package br.com.escola.dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FaltasProperty {
	private SimpleStringProperty nome;
	private SimpleIntegerProperty jan;
	private SimpleIntegerProperty fev;
	private SimpleIntegerProperty mar;
	private SimpleIntegerProperty abr;
	private SimpleIntegerProperty mai;
	private SimpleIntegerProperty jun;
	private SimpleIntegerProperty jul;
	private SimpleIntegerProperty ago;
	private SimpleIntegerProperty set;
	private SimpleIntegerProperty out;
	private SimpleIntegerProperty nov;
	private SimpleIntegerProperty dez;

	public FaltasProperty(String nome, int jan, int fev, int mar, int abr, int mai, int jun, int jul, int ago, int set,
			int out, int nov, int dez) {
		this.nome = new SimpleStringProperty(nome);
		this.jan = new SimpleIntegerProperty(jan);
		this.fev = new SimpleIntegerProperty(fev);
		this.mar = new SimpleIntegerProperty(mar);
		this.abr = new SimpleIntegerProperty(abr);
		this.mai = new SimpleIntegerProperty(mai);
		this.jun = new SimpleIntegerProperty(jun);
		this.jul = new SimpleIntegerProperty(jul);
		this.ago = new SimpleIntegerProperty(ago);
		this.set = new SimpleIntegerProperty(set);
		this.out = new SimpleIntegerProperty(out);
		this.nov = new SimpleIntegerProperty(nov);
		this.dez = new SimpleIntegerProperty(dez);
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public int getJan() {
		return jan.get();
	}

	public void setJan(int jan) {
		this.jan.set(jan);
	}

	public int getFev() {
		return fev.get();
	}

	public void setFev(int fev) {
		this.fev.set(fev);
	}

	public int getMar() {
		return mar.get();
	}

	public void setMar(int mar) {
		this.mar.set(mar);
	}

	public int getAbr() {
		return abr.get();
	}

	public void setAbr(int abr) {
		this.abr.set(abr);
	}

	public int getMai() {
		return mai.get();
	}

	public void setMai(int mai) {
		this.mai.set(mai);
	}

	public int getJun() {
		return jun.get();
	}

	public void setJun(int jun) {
		this.jun.set(jun);
	}

	public int getJul() {
		return jul.get();
	}

	public void setJul(int jul) {
		this.jul.set(jul);
	}

	public int getAgo() {
		return ago.get();
	}

	public void setAgo(int ago) {
		this.ago.set(ago);
	}

	public int getSet() {
		return set.get();
	}

	public void setSet(int set) {
		this.set.set(set);
	}

	public int getOut() {
		return out.get();
	}

	public void setOut(int out) {
		this.out.set(out);
	}

	public int getNov() {
		return nov.get();
	}

	public void setNov(int nov) {
		this.nov.set(nov);
	}

	public int getDez() {
		return dez.get();
	}

	public void setDez(int dez) {
		this.dez.set(dez);
	}

}
