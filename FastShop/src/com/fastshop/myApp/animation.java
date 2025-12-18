/*
 * Here are the complete code snippets with imports:

*Slide In/Out Animation*
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // To show the layout
    linearLayout.animate().translationX(0).setDuration(500).start();

    // To hide the layout
    linearLayout.animate().translationX(-linearLayout.getWidth()).setDuration(500).start();
}
*Fade In/Out Animation*
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // To show the layout
    linearLayout.setAlpha(0);
    linearLayout.setVisibility(View.VISIBLE);
    linearLayout.animate().alpha(1).setDuration(500).start();

    // To hide the layout
    linearLayout.animate().alpha(0).setDuration(500).withEndAction(new Runnable() {
        @Override
        public void run() {
            linearLayout.setVisibility(View.GONE);
        }
    }).start();
}
*Scale Animation*
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // To show the layout
    linearLayout.setScaleX(0);
    linearLayout.setScaleY(0);
    linearLayout.setVisibility(View.VISIBLE);
    linearLayout.animate().scaleX(1).scaleY(1).setDuration(500).start();

    // To hide the layout
    linearLayout.animate().scaleX(0).scaleY(0).setDuration(500).withEndAction(new Runnable() {
        @Override
        public void run() {
            linearLayout.setVisibility(View.GONE);
        }
    }).start();
}
*Slide and Fade Animation*
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // To show the layout
    linearLayout.setTranslationX(-linearLayout.getWidth());
    linearLayout.setAlpha(0);
    linearLayout.setVisibility(View.VISIBLE);
    linearLayout.animate().translationX(0).alpha(1).setDuration(500).start();

    // To hide the layout
    linearLayout.animate().translationX(-linearLayout.getWidth()).alpha(0).setDuration(500).withEndAction(new Runnable() {
        @Override
        public void run() {
            linearLayout.setVisibility(View.GONE);
        }
    }).start();
}
*Overshoot Animation*
import android.animation.ObjectAnimator;
import android.view.animation.OvershootInterpolator;

public class MainActivity extends AppCompatActivity {
    // Show sidebar animation
    ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "translationX", -linearLayout.getWidth(), 0);
    animator.setDuration(500);
    animator.setInterpolator(new OvershootInterpolator());
    animator.start();
}
 */