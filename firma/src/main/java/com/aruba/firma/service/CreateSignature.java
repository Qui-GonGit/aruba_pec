package com.aruba.firma.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.Date;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aruba.firma.service.exception.SignException;
import com.aruba.firma.service.pojo.User;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PrivateKeySignature;


@Service
public class CreateSignature {
	@Value("${private.key.global.path}")
	String keyPath;
	@Value("${document.global.path}")
	String globalPath;
	private static final Logger logger = LoggerFactory.getLogger(CreateSignature.class);
	public String signDetached(User user) throws SignException {
		try (FileInputStream inFile = new FileInputStream(keyPath + user.getPrivateKey() + "\\keystore.p12")) {
			Security.addProvider(new BouncyCastleProvider());
			KeyStore ks = KeyStore.getInstance("pkcs12");
			logger.debug("get keystore PKCS12");
			ks.load(inFile, user.getPasswordKey().toCharArray());
			String alias = ks.aliases().nextElement();
			PrivateKey pk = (PrivateKey) ks.getKey(alias, user.getPasswordKey().toCharArray());
			Certificate[] chain = ks.getCertificateChain(alias);
			String documentiPath = user.getDocRelPath();
			PdfReader reader = new PdfReader(globalPath +documentiPath);
			String substring = documentiPath.substring(0, documentiPath.lastIndexOf('.'));
			String filename = globalPath + substring + "_signed_" + new Date().getTime() + ".pdf";
			FileOutputStream fos = new FileOutputStream(filename);
			PdfStamper stamper = PdfStamper.createSignature(reader, fos, '\0');
			PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
			appearance.setReason("Aruba signing");
			appearance.setLocation("ITA");
			ExternalSignature es = new PrivateKeySignature(pk, "SHA-256", "BC");
			ExternalDigest digest = new BouncyCastleDigest();
			MakeSignature.signDetached(appearance, digest, es, chain, null, null, null, 0, CryptoStandard.CMS);
			return filename;
		} catch (Exception e) {
			logger.error("signDetach error : {}", e.getMessage());
			throw new SignException("Errore nel processo di firma");
		}
	}
}
