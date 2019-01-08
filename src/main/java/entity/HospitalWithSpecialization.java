package entity;

import java.util.HashSet;
import java.util.Set;

public class HospitalWithSpecialization {

  private Long id;

  private String name;

  private String urc;

  private String phone;
  
  private Address addressDTO;
  
  private String webSite;
  
  private String email;
  
  private Set<Specialization> specializationDTOs = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrc() {
    return urc;
  }

  public void setUrc(String urc) {
    this.urc = urc;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Address getAddressDTO() {
    return addressDTO;
  }

  public void setAddressDTO(Address addressDTO) {
    this.addressDTO = addressDTO;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Specialization> getSpecializationDTOs() {
    return specializationDTOs;
  }

  public void setSpecializationDTOs(Set<Specialization> specializationDTOs) {
    this.specializationDTOs = specializationDTOs;
  }
}
