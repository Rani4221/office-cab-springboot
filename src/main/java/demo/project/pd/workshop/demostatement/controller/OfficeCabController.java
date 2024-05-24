package demo.project.pd.workshop.demostatement.controller;

import demo.project.pd.workshop.demostatement.model.OfficeCab;
import demo.project.pd.workshop.demostatement.repository.OfficeCabRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;

@Controller

public class OfficeCabController {

    @Autowired
    private OfficeCabRepository officeCabRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    @GetMapping("/user/list")
    public String getData(@RequestParam(name = "search", required = false) String search, Model model) {
        List<OfficeCab> officeCabList;



            officeCabList = officeCabRepository.findAll();

//        officeCabList = officeCabRepository.findAll();
        model.addAttribute("officeCabList", officeCabList);
        return "list";
    }


    @GetMapping("/admin/personal-list")
    public String personalList(@RequestParam(name = "search", required = false) String search, Model model) {
        List<OfficeCab> userCabs;


            userCabs = officeCabRepository.findAll();


        model.addAttribute("userCabs", userCabs);
        return "personal-list";
    }

@GetMapping("/")
public String getWelcome(){
        return "welcome";
}

    @GetMapping("/user/register")

    public String registrationForm(){
    return "registration";

}

    @PostMapping("/user/register")
    public String submitRegistrationForm(OfficeCab officeCab) {
    officeCabRepository.save(officeCab);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mishrasriyarani21@gmil.com");
        message.setTo(officeCab.getEmailId());
        message.setCc("sriyaranimishra2001@gmail.com");
        message.setSubject("Confirmation For Booking Cab for  "+ officeCab.getName());
        String text = "Dear " + officeCab.getName() + ",\n\n";
        text += "Thank you for booking a cab. Your booking has been confirmed.\n";
        text += "Details of your booking:\n";
        text += "Name: " + officeCab.getName() + "\n";
        text += "Cab Number: " + officeCab.getCabNumber() + "\n";


        message.setText(text);

        javaMailSender.send(message);
        return "redirect:/user/success";
    }



        @GetMapping("/user/success")
    public String registrationSuccess() {
        return "success";
    }


    @GetMapping("/user/get/{id}")

    public String getById(@PathVariable("id") Long id, OfficeCab officeCab,Model model){
    OfficeCab officeCab1=officeCabRepository.findById(id).get();
    model.addAttribute("userCabs",officeCab1);

    return "list";
    }


    @GetMapping("/user/update/{id}")
    public  String  editDetails(@PathVariable("id") Long id, Model model){

            OfficeCab officeCab = officeCabRepository.findById(id).get();
            model.addAttribute("officeCab", officeCab);
            return "update-register" ;
         }

         @PostMapping("/user/update/{id}")
    public  String updateDetails(@ModelAttribute("officeCab") OfficeCab officeCab){
       officeCabRepository.save(officeCab);
       return  "redirect:/user/list";
         }




}







