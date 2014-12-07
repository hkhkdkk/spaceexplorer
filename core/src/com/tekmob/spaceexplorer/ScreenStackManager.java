package com.tekmob.spaceexplorer;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class ScreenStackManager {
	
	private Game game;
	private Stack<Screen> stack;
	
	public ScreenStackManager(Game game, Screen firstScreen) {
		this.game = game;
		this.stack = new Stack<Screen>();
		push(firstScreen);
	}

	public void push(Screen sc) {
		stack.push(sc);
		game.setScreen(sc);
	}

	public void pop() {
		Screen tmp = stack.pop();
		if(!stack.isEmpty()) game.setScreen(stack.peek());
		else Gdx.app.exit();
		tmp.dispose();
	}
	
	public void changeScreen(Screen sc) {
		Screen tmp = stack.pop();
		push(sc);
		tmp.dispose();
	}
}
