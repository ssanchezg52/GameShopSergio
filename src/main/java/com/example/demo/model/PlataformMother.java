package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class PlataformMother {
	
	public static ArrayList<Plataform> getPlataformList() {
		Plataform plataform = new Plataform("Xbox One", "https://i.ibb.co/X2f5MDq/xbox-One-Icon.png", List.of());
		Plataform plataformDos = new Plataform("PlayStation4", "https://i.ibb.co/kGSHP2b/pngwing-com.png", List.of());
		Plataform plataformTres = new Plataform("Xbox Series", "https://i.ibb.co/X2f5MDq/xbox-One-Icon.png", List.of());
		Plataform plataformCuatro = new Plataform("PlayStation5", "https://i.ibb.co/kGSHP2b/pngwing-com.png", List.of());
		Plataform plataformCinco = new Plataform("PC", "https://i.ibb.co/pPj5vgz/Png-Item-1141447.png", List.of());
		Plataform plataformSeis = new Plataform("Nintendo Switch", "https://i.ibb.co/GvVr7Hq/Nintendo-Switch.png", List.of());
		return new ArrayList<>(List.of(plataform,plataformDos,plataformTres,plataformCuatro,plataformCinco,plataformSeis));
	}

}
