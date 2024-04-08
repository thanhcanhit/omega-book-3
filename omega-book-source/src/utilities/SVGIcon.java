/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.awt.Color;
import java.io.File;

import javax.swing.Icon;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;

/**
 *
 * @author thanhcanhit
 */
public class SVGIcon {
	private static final Color LIGHT_COLOR = FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.white);
	private static final Color DARK_COLOR = FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.white);

	public static Icon getSVGIcon(String path) {
		FlatSVGIcon icon = new FlatSVGIcon(new File(path));
		FlatSVGIcon.ColorFilter f = new FlatSVGIcon.ColorFilter();
		f.add(Color.decode("#969696"), DARK_COLOR, LIGHT_COLOR);
		icon.setColorFilter(f);
		return icon;
	}

	public static Icon getPrimarySVGIcon(String path) {
		FlatSVGIcon icon = new FlatSVGIcon(new File(path));
		FlatSVGIcon.ColorFilter f = new FlatSVGIcon.ColorFilter();
		f.add(Color.decode("#969696"), LIGHT_COLOR, DARK_COLOR);
		icon.setColorFilter(f);
		return icon;
	}
}
