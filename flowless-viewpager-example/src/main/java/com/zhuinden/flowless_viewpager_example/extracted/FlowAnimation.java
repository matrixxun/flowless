package com.zhuinden.flowless_viewpager_example.extracted;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.io.Serializable;

import flowless.Direction;

/**
 * Created by Zhuinden on 2016.06.27..
 */
public abstract class FlowAnimation
        implements Serializable {
    public static FlowAnimation NONE = new FlowAnimation() {
        @Nullable
        @Override
        public Animator createAnimation(@NonNull View previousView, @NonNull View newView, Direction direction) {
            return null;
        }

        @Override
        public boolean showChildOnTopWhenAdded(Direction direction) {
            return true;
        }
    };

    public static FlowAnimation SEGUE = new FlowAnimation() {
        @Nullable
        @Override
        public Animator createAnimation(@NonNull View previousView, @NonNull View newView, Direction direction) {
            if(direction == Direction.REPLACE) {
                return null;
            }
            boolean backward = direction == Direction.BACKWARD;
            int fromTranslation = backward ? previousView.getWidth() : -previousView.getWidth();
            int toTranslation = backward ? -newView.getWidth() : newView.getWidth();

            AnimatorSet set = new AnimatorSet();

            set.play(ObjectAnimator.ofFloat(previousView, View.TRANSLATION_X, fromTranslation));
            set.play(ObjectAnimator.ofFloat(newView, View.TRANSLATION_X, toTranslation, 0));

            return set;
        }

        @Override
        public boolean showChildOnTopWhenAdded(Direction direction) {
            return true;
        }
    };

    @Nullable
    public abstract Animator createAnimation(@NonNull View previousView, @NonNull View newView, Direction direction);

    public abstract boolean showChildOnTopWhenAdded(Direction direction);

    @Override
    public boolean equals(Object object) {
        return object instanceof FlowAnimation || this == object;
    }

    @Override
    public int hashCode() {
        return FlowAnimation.class.getName().hashCode();
    }
}
