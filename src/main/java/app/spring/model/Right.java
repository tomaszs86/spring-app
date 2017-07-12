package app.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rights")
public class Right implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idRight;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_right")
	public Long getIdRight() {
		return idRight;
	}

	public void setIdRight(Long idRight) {
		this.idRight = idRight;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
