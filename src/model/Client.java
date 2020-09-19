package model;

import java.io.Serializable;

public class Client implements Serializable{
	
	private static final long serialVersionUID = 1;
	private String name;
	private String lastName;
	private String idNum;
	private String idType;
	private String telephone;
	private String address;

	/**
	 * 
	 * @param idType
	 * @param idNum
	 * @param name
	 * @param telephone
	 * @param address
	 */
	public Client(String name, String lastName, String idNum, String idType, String telephone, String address) {
		this.name = name;
		this.lastName = lastName;
		this.idNum = idNum;
		this.idType = idType;
		this.telephone = telephone;
		this.address = address;
		
	}

	public String getIdType() {
		return idType;
	}

	/**
	 * 
	 * @param idType
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNum() {
		return idNum;
	}

	/**
	 * 
	 * @param idNum
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	/**
	 * 
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param adress
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getInfo() {
		String info = "";
		info += "Name: " + name + "\nLast name: " + lastName + "\nIdType: " + idType + "\nidNum: " + idNum + "\nTelephone: " + telephone + "\nAdress: " + address + "\n";
		return info;
	}

}