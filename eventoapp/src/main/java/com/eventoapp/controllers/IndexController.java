package com.eventoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.models.Evento;
import com.eventoapp.models.Role;
import com.eventoapp.models.Usuario;
import com.eventoapp.repository.EventoRepository;
import com.eventoapp.repository.UsuarioRepository;

@Controller
public class IndexController {
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private UsuarioRepository ur;

	@RequestMapping("/")
	public String index() {
		Usuario u = new Usuario();
		u.setLogin("kiko");
		u.setNomeCompleto("Kiko Prado");
		u.setSenha(new BCryptPasswordEncoder().encode("kiko"));
		
		Role role = new Role();
		role.setNomeRole("ROLE_ADMIN");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		u.setRoles(roles);
		
		ur.save(u);
		
		return "index";
	}
	
//	@RequestMapping("/")
//	public ModelAndView listaEventos() {
//		ModelAndView mv = new ModelAndView("index");
//		Iterable<Evento> eventos = er.findAll();
//		mv.addObject("eventos", eventos);
//		return mv;
//	}
}
