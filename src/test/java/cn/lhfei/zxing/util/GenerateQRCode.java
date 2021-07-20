/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.zxing.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created May 20, 2021
 */
public class GenerateQRCode {

  public static void main(String[] args) throws WriterException, IOException {
      String qrCodeText = "https://github.com/lhfei";
      String filePath = "JD.png";
      int size = 125;
      String fileType = "png";
      File qrFile = new File(filePath);
      createQRImage(qrFile, qrCodeText, size, fileType);
      System.out.println("DONE");
  }

  private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
          throws WriterException, IOException {
      // Create the ByteMatrix for the QR-Code that encodes the given String
      Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
      hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
      // Make the BufferedImage that are to hold the QRCode
      int matrixWidth = byteMatrix.getWidth();
      BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
      image.createGraphics();

      Graphics2D graphics = (Graphics2D) image.getGraphics();
      graphics.setColor(Color.WHITE);
      graphics.fillRect(0, 0, matrixWidth, matrixWidth);
      // Paint and save the image using the ByteMatrix
      graphics.setColor(Color.BLACK);

      for (int i = 0; i < matrixWidth; i++) {
          for (int j = 0; j < matrixWidth; j++) {
              if (byteMatrix.get(i, j)) {
                  graphics.fillRect(i, j, 1, 1);
              }
          }
      }
      ImageIO.write(image, fileType, qrFile);
  }

}