package modelConverter;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ResultConverter {
	
	private String epnumber;
	private String result;
	//private String examDate;
	

}
