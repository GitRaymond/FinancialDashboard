package com.raymond.financialdashboard.domain.seeder.partial;

import com.raymond.financialdashboard.domain.enumeration.Sign;
import com.raymond.financialdashboard.domain.seeder.SeederInterface;
import com.raymond.financialdashboard.service.TransactionIngService;
import com.raymond.financialdashboard.service.dto.TransactionIngDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.*;


@Component
public class IngSeeder implements SeederInterface {

    @Autowired
    private TransactionIngService transactionIngService;

    private static final String SPLIT_ON = ";" ;

    @Override
    public void init(){
        this.processInputFile("D:\\Projects\\financialdashboard\\data\\NL70INGB0003665457_01-01-2017_03-07-2018.csv");
    }

    public void processInputFile(String inputFilePath) {
//        List<TransactionIngDTO> inputList = new ArrayList<>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            br.lines().skip(1).forEach(this::mapToItem);
//            br.lines().skip(1).forEach(System.out::println);
//            br.lines().skip(1).forEach( s ->{
//                this.mapToItem(s);
//            });

            br.close();
//
//            List<Integer> numberList = Arrays.asList(0,1,1,2,3,5,8,13,21,34,55);
//
//            // so print all the squares of the even number of the numberList
//            numberList.stream().filter(n -> n%2 == 0).map(n -> n*n ).forEach(System.out::println);

        } catch (IOException e) {
            System.out.println(e);
        }

    }


    private TransactionIngDTO mapToItem(String line) {
        System.out.println("test");
        String p[] = line.split(SPLIT_ON); // a CSV has comma separated lines

        TransactionIngDTO item = new TransactionIngDTO();

        item.setDate( Integer.parseInt(p[0]));
        item.setName(p[1]);
        item.setMyBankAccount(p[2]);
        if (p[3] != null && p[3].trim().length() > 0) {
            item.setContraBankAccount(p[3]);
        }
        item.setCode(p[4]);
        item.setSign(p[5].equals("Af") ? Sign.MINUS : Sign.PLUS);
        item.setAmount(Double.parseDouble(p[6].replace(",", ".")));
        item.setMutation(p[7]);
        if( p.length > 8 ) {
            item.setDescription(p[8]);
        }

        return this.transactionIngService.save(item);

    };

}
