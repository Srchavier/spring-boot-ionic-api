package com.cursomc.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cursomc.service.exception.FileException;

@Service
public class ImagemService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}

		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpg(img);

			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}

	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImagem = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImagem.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImagem;
	}

	public InputStream getInputStream(BufferedImage img, String extensao) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extensao, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (Exception e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	public BufferedImage cropImagem(BufferedImage img) {
		int min = (img.getHeight() <= img.getWidth()) ? img.getHeight() : img.getWidth();
		// @formatter:off
		return Scalr.crop(
				img,
				(img.getWidth() / 2) - (min / 2),
				(img.getHeight() / 2) - (min / 2),
				min,
				min);
		// @formatter:on

	}

	public BufferedImage redimensionarImagem(BufferedImage img, int tamanho) {
		return Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, tamanho);
	}

}
