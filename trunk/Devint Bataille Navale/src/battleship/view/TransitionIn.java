package battleship.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

/**
 * A transition to fade in from a given colour
 *
 * @author H
 */
public class TransitionIn implements Transition {

        private Color color;
        private int fadeTime = 500;

        /**
         * Create a new fade in transition
         */
        public TransitionIn() {
                this(Color.black, 500);
        }
        
        /**
         * Create a new fade in transition
         * 
         * @param color The color we're going to fade in from
         */
        public TransitionIn(Color color) {
                this(color, 500);
        }
        
        /**
         * Create a new fade in transition
         * 
         * @param color The color we're going to fade in from
         * @param fadeTime The time it takes for the fade to occur
         */
        public TransitionIn(Color color, int fadeTime) {
                this.color = new Color(color);
                this.color.a = 1;
                this.fadeTime = fadeTime;
        }
        
        /**
         * @see org.newdawn.slick.state.transition.Transition#isComplete()
         */
        public boolean isComplete() {
                return (color.a <= 0);
        }

        /**
         * @see org.newdawn.slick.state.transition.Transition#postRender(org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
         */
        public void postRender(StateBasedGame game, GameContainer container, Graphics g) {
                Color old = g.getColor();
                g.setColor(color);
                
                g.fillRect(0, 0, container.getWidth()*2, container.getHeight()*2);
                g.setColor(old);
        }
        
        /**
         * @see org.newdawn.slick.state.transition.Transition#update(org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.GameContainer, int)
         */
        public void update(StateBasedGame game, GameContainer container, int delta) {
                color.a -= delta * (1.0f / fadeTime);
                if (color.a < 0) {
                        color.a = 0;
                }
        }

        /**
         * @see org.newdawn.slick.state.transition.Transition#preRender(org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
         */
        public void preRender(StateBasedGame game, GameContainer container, Graphics g) {
        }

        public void init(GameState firstState, GameState secondState) {
                // TODO Auto-generated method stub
                
        }

}