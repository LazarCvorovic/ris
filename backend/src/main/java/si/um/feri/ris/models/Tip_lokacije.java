package si.um.feri.ris.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tip_lokacije {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idTipLokacije;

        private String opis;

        private int povrsina;

        // Getters and Setters

        public int getPovrsina() {
            return povrsina;
        }

        public void setPovrsina(int povrsina) {
            this.povrsina = povrsina;
        }

        public Long getIdTipLokacije() {
            return idTipLokacije;
        }

        public void setIdTipLokacije(Long idTipLokacije) {
            this.idTipLokacije = idTipLokacije;
        }

        public String getOpis() {
            return opis;
        }

        public void setOpis(String opis) {
            this.opis = opis;
        }
    }


