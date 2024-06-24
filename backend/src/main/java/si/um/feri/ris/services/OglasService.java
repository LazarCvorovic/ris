package si.um.feri.ris.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Oglas;
import si.um.feri.ris.models.Uporabnik;
import si.um.feri.ris.repositories.OcenaRepository;
import si.um.feri.ris.repositories.OglasRepository;
import si.um.feri.ris.repositories.UporabnikRepository;

import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class OglasService {

    @Autowired
    private OglasRepository oglasRepository;

    @Autowired
    private OcenaRepository ocenaRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public List<Oglas> getAllOglasi() {
        return oglasRepository.findAll();
    }

    public Optional<Oglas> getOglasById(Long id) {
        return oglasRepository.findById(id);
    }

    public Oglas createOglas(Oglas oglas) {
        return oglasRepository.save(oglas);
    }

    public Optional<Oglas> updateOglas(Long id, Oglas oglasDetails) {
        return oglasRepository.findById(id).map(oglas -> {
            oglas.setOpis(oglasDetails.getOpis());
            oglas.setDatumOd(oglasDetails.getDatumOd());
            oglas.setDatumDo(oglasDetails.getDatumDo());
            oglas.setAdresa(oglasDetails.getAdresa());
            oglas.setOtkazano(oglasDetails.isOtkazano());
            oglas.setRegija(oglasDetails.getRegija());
            oglas.setNaslov(oglasDetails.getNaslov());
            oglas.setTelefonskaStevilka(oglasDetails.getTelefonskaStevilka());
            oglas.setEnaslov(oglasDetails.getEnaslov());
            oglas.setLokacija(oglasDetails.getLokacija());
            return oglasRepository.save(oglas);
        });
    }


    @Transactional
    public boolean deleteOglas(Long id) {
        if (oglasRepository.existsById(id)) {
            ocenaRepository.deleteByOglasId(id); // Prvo obriši sve ocene povezane sa oglasom
            oglasRepository.deleteById(id); // Zatim obriši oglas
            return true;
        } else {
            return false;
        }
    }


    public List<Oglas> findByRegijaAndDatumOdAfter(String regija, LocalDate datumOd) {
        return oglasRepository.findByRegijaAndDatumOdAfter(regija, datumOd);
    }

    public List<Oglas> findByRegijaAndDatumOdAfterAndOtkazano(String regija, LocalDate datumOd, boolean otkazano) {
        return oglasRepository.findByRegijaAndDatumOdAfterAndOtkazano(regija, datumOd, otkazano);
    }

    public List<Oglas> findByMestoAndOtkazano(String mesto, boolean otkazano) {
        return oglasRepository.findByMestoAndOtkazano(mesto, otkazano);
    }

    public List<Oglas> findByAdresa(String adresa) {
        return oglasRepository.findByAdresa(adresa);
    }

    public List<Oglas> findByRegija(String regija) {
        return oglasRepository.findByRegija(regija);
    }

    public List<Oglas> findByDatumOdAfter(LocalDate datumOd) {
        return oglasRepository.findByDatumOdAfter(datumOd);
    }

    public List<Oglas> findByDatumDoBefore(LocalDate datumDo) {
        return oglasRepository.findByDatumDoBefore(datumDo);
    }

    public List<Oglas> findByFilters(String regija, LocalDate datumOd, LocalDate datumDo, String adresa) {
        return oglasRepository.findByFilters(regija, datumOd, datumDo, adresa);
    }

    public void createOglasAndGeneratePdf(Oglas oglas) throws DocumentException, IOException {
        Oglas savedOglas = oglasRepository.save(oglas);

        ByteArrayOutputStream pdfStream = generatePdfForOglas(savedOglas);

        String desktopPath = System.getProperty("user.home") + "\\Desktop";
        String folderPath = desktopPath + "\\pdf";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = folderPath + "\\oglas_details.pdf";
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        pdfStream.writeTo(fileOutputStream);
        fileOutputStream.close();

        System.out.println("PDF sačuvan na: " + filePath);
    }

    private ByteArrayOutputStream generatePdfForOglas(Oglas oglas) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Paragraph paragraph = new Paragraph("Oglas Details", boldFont);
        document.add(paragraph);
        document.add(new Paragraph("Opis: " + oglas.getOpis()));
        document.add(new Paragraph("Datum Od: " + oglas.getDatumOd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        document.add(new Paragraph("Datum Do: " + oglas.getDatumDo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        document.add(new Paragraph("Adresa: " + oglas.getAdresa()));
        document.add(new Paragraph("Otkazano: " + (oglas.isOtkazano() ? "Da" : "Ne")));
        document.add(new Paragraph("Regija: " + oglas.getRegija()));
        document.add(new Paragraph("Naslov: " + oglas.getNaslov()));
        document.add(new Paragraph("Telefonska Stevilka: " + oglas.getTelefonskaStevilka()));
        document.add(new Paragraph("Email: " + oglas.getEnaslov()));

        document.close();
        return outputStream;
    }

    public void applyToOglas(Oglas oglas) throws MessagingException, DocumentException, IOException {
        System.out.println("Starting applyToOglas for oglas: " + oglas.getNaslov());
        try {
            posaljiEmail(oglas);
            System.out.println("Email sent successfully for oglas: " + oglas.getNaslov());
        } catch (Exception e) {
            System.err.println("Error in applyToOglas: " + e.getMessage());
            throw e;
        }
    }

    private void posaljiEmail(Oglas oglas) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        try {
            System.out.println("Sending email to: " + oglas.getEnaslov());
            helper.setTo(oglas.getEnaslov());
            helper.setSubject("Nova Ponuda za Oglas");
            helper.setText("Spoštovani,\n\nDobili ste novo ponudbo za vaš oglas: " + oglas.getNaslov() +
                    "\n\nPodrobnosti oglasa:\n" +
                    "Opis: " + oglas.getOpis() + "\n" +
                    "Datum Od: " + oglas.getDatumOd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n" +
                    "Datum Do: " + oglas.getDatumDo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n" +
                    "Adresa: " + oglas.getAdresa() + "\n" +
                    "Telefonska Stevilka: " + oglas.getTelefonskaStevilka() + "\n\n" +
                    "Hvala vam, ker uporabljate našo storitev.");
            javaMailSender.send(msg);
            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}