package cat.itb.projectespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patinet {

    @NotNull
    @NotEmpty
    private String nomPatinet;

    @NotNull
    @NotEmpty
    private String marcaPatinet;

    public String getNomPatinet() {
        return nomPatinet;
    }

    public void setNomPatinet(String nomPatinet) {
        this.nomPatinet = nomPatinet;
    }

    public String getMarcaPatinet() {
        return marcaPatinet;
    }

    public void setMarcaPatinet(String marcaPatinet) {
        this.marcaPatinet = marcaPatinet;
    }
}
