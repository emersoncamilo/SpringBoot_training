package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
   
    private String publicPlace;
    private String zipCode;
    private String district;
    private String city;
    private String numberAddress;
    private String complement;
    
    public Address(AddressData addressData) {
        this.publicPlace = addressData.publicPlace();
        this.zipCode = addressData.zipCode();
        this.district = addressData.district();
        this.city = addressData.city();
        this.numberAddress = addressData.numberAddress();
        this.complement = addressData.complement();
    }

    public void updateInfo(AddressData dataUpdate) {
        if (dataUpdate.publicPlace() != null) {
            this.publicPlace = dataUpdate.publicPlace();
        }
        if (dataUpdate.district() != null) {
            this.district = dataUpdate.district();
        }
        if (dataUpdate.zipCode() != null) {
            this.zipCode = dataUpdate.zipCode();
        }
        if (dataUpdate.city() != null) {
            this.city = dataUpdate.city();
        }
        if (dataUpdate.numberAddress() != null) {
            this.numberAddress = dataUpdate.numberAddress();
        }
        if (dataUpdate.complement() != null) {
            this.complement = dataUpdate.complement();
        }
    }
}