package com.victor.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.victor.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString("Coins: " + Game.moedas_atual + "/" + Game.moedas_cont, 32, 20);
	}
	
}
