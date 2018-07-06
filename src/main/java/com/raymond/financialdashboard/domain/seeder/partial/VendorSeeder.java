package com.raymond.financialdashboard.domain.seeder.partial;


import com.raymond.financialdashboard.domain.seeder.SeederInterface;
import com.raymond.financialdashboard.service.VendorService;
import com.raymond.financialdashboard.service.dto.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class VendorSeeder implements SeederInterface {


    private List<String> vendors = new ArrayList<>();

    @Autowired
    private VendorService vendorService;

    @Override
    public void init(){
        this.fillList();
        this.storeVendorsToDataBase();
    }

    private void storeVendorsToDataBase() {
        for(String vendor:vendors){
            VendorDTO item = new VendorDTO();
            item.setName(vendor);
            vendorService.save(item);
        }
    }

    private void fillList(){

        vendors.add("Albert Heijn");
        vendors.add("Sodexo");


    }



}


