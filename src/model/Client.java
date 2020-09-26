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
	 * This method is the constructor of client
	 * @param name is the name
	 * @param lastName is the last name
	 * @param idNum is the ID number
	 * @param idType is the  ID type
	 * @param telephone is the telephone
	 * @param address is the address
	 */
	public Client(String name, String lastName, String idNum, String idType, String telephone, String address) {
		this.name = name;
		this.lastName = lastName;
		this.idNum = idNum;
		this.idType = idType;
		this.telephone = telephone;
		this.address = address;

	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return the idNum
	 */
	public String getIdNum() {
		return idNum;
	}
	/**
	 * @param idNum the idNum to set
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}
	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * This method concatenate the name to the last name
	 * @return the full name
	 */
	public String getFullName() {
		String fullName = name + " " + lastName;
		return fullName;
	}
	/**
	 * This method concatenate the info of the client
	 * @return the info of the client concatenated
	 */
	public String getInfo() {
		String info = "";
		info += "Name: " + name + "\nLast name: " + lastName + "\nIdType: " + idType + "\nidNum: " + idNum + "\nTelephone: " + telephone + "\nAdress: " + address + "\n";
		return info;
	}

}