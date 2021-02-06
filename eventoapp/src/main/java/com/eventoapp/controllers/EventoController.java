package com.eventoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;


	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarEvento";
		}
		
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso");
		
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("eventos");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo) {
		Evento evento = er.findByCodigo(codigo);
		
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		for(Convidado convidado : convidados) {
			convidado.getEvento().remove(evento);
			cr.save(convidado);
		}
		
		er.delete(evento);
		return "redirect:/eventos";
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		
		Evento evento = er.findByCodigo(codigo);
		
		try {
			Convidado c = cr.findByRg(convidado.getRg());
			c.getEvento().add(evento);
			
			//Essa linha é opcional. Com isso o convidado atualiza seu nome de cadastro
			//com o mesmo RG, sem ela fica o nome cadastrado da primeira vez
			//c.setNomeConvidado(convidado.getNomeConvidado());
			
			cr.save(c);
		} catch (Exception e){
			System.out.println("Teste");
			convidado.setEvento(new ArrayList<Evento>());
			convidado.getEvento().add(evento);
			cr.save(convidado);
		}
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg, long codigo) {
		Convidado convidado = cr.findByRg(rg);
		Evento evento = er.findByCodigo(codigo);
		convidado.getEvento().remove(evento);
		cr.save(convidado);
		
		return "redirect:/" + codigo;
	}
}
