package com.prithvi.try2;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImageProcessor implements Processor {

	@Override  
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		int exifFlag=0;
		if (exchange!=null) {
			/*String out=exchange.getIn().getBody(String.class);
			String path[]=out.split("=");*/
			File f=exchange.getIn().getBody(File.class);
			try {
			Metadata imageData=ImageMetadataReader.readMetadata(f/*new File(path[1])*/);
			
			for (Directory directory:imageData.getDirectories()) {
				if (directory.getName().split(" ")[0].toLowerCase().equals("exif")) {
					++exifFlag;
					for (Tag tag : directory.getTags()) {
						if (tag.getTagName().split(" ")[0].toLowerCase().equals("date/time")) {
						Date date =new Date(tag.getDescription());
						String destination=f.getParent()+"\\Folderify\\"+date.getYear()+"\\"+date.getMonth()+"\\";
						Path from=Paths.get(f.getAbsolutePath());
						Path to = Paths.get(destination); 
						if (!Files.exists(to)) {
							   try {
							      Files.createDirectories(to);
							   } catch (IOException ioe) {
							      ioe.printStackTrace();
							   }
						}
						Files.copy(from, new File(destination+f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
						System.out.println(f.getPath()+"->"+destination);
						Files.delete(from);
						}
						}
				}
				
			
			}
			if(exifFlag==0) {
			String destination=f.getParent()+"\\non-exif\\";
			System.out.println("non-exif file -> "+f.getName());
			Path from=Paths.get(f.getAbsolutePath());
			Path to = Paths.get(destination); 
			if (!Files.exists(to)) {
				   try {
				      Files.createDirectories(to);
				   } catch (IOException ioe) {
				      ioe.printStackTrace();
				   }
			}
			Files.copy(from, new File(destination+f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println(f.getPath()+"->"+destination);
			Files.delete(from);
		}
		}catch(ImageProcessingException IE) {
			System.out.println(f.getName()+" is not processable");
		}catch(NoSuchFileException NF) {
			System.out.println(f.getAbsolutePath()+" doesn't exist");
		}catch (FileSystemException FSE) {
			System.out.println("Wait for process to end");
		}
		}
		exchange.getIn().setBody(null);
	}

}
