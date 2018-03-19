package com.prithvi.try2;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImageTest {
@Test
public void testImage() throws ImageProcessingException, IOException {
	File f=new File("C:\\Users\\Prithvijit Dasgupta\\Pictures\\DCIM\\Camera\\.aux\\P_20160623_185213_LL_1.jpg");
	Metadata imageData=ImageMetadataReader.readMetadata(f);
	for (Directory directory:imageData.getDirectories()) {
		for (Tag tag:directory.getTags()) {
			System.out.println(directory.getName()+tag.getTagName()+tag.getDescription());
		}
	}
}
}
