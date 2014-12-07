package com.tekmob.spaceexplorer;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenStackManager {
	
	private Game game;
	private Stack<Screen> stack;
	
	public ScreenStackManager(Game game) {
		this.game = game;
		this.stack = new Stack<Screen>();
	}

	public void push(Screen sc) {
		stack.push(sc);
		if(!stack.isEmpty()) game.setScreen(stack.peek());game.setScreen(stack.peek());
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public void pop() {
		Screen tmp = stack.pop();
		if(!stack.isEmpty()) game.setScreen(stack.peek());
		tmp.dispose();
	}
	
	public void changeScreen(Screen sc) {
		Screen tmp = stack.pop();
		stack.push(sc);
		if(!stack.isEmpty()) game.setScreen(stack.peek());
		tmp.dispose();
	}
}
