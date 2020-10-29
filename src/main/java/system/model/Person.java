package system.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import system.config.StringPrefixedSequenceIdGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {
	@Id
	 @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @GenericGenerator(   
        name = "id_seq", 
       strategy = "system.config.StringPrefixedSequenceIdGenerator",
       parameters = {
    		   @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
    		   @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EMPL"),
    		   @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	protected String id;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;
    
    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    protected Gender gender;

}