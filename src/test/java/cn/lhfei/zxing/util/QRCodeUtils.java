/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.lhfei.zxing.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created May 18, 2021
 */

public class QRCodeUtils {
  public static String deEncodeByPath(String path) {
    String content = null;
    BufferedImage image;
    try {
      image = ImageIO.read(new File(path));
      LuminanceSource source = new BufferedImageLuminanceSource(image);
      Binarizer binarizer = new HybridBinarizer(source);
      BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
      Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
      hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
      Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 解码
      System.out.println(result.getText());
      content = result.getText();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (com.google.zxing.NotFoundException e) {
      e.printStackTrace();
    }
    return content;
  }

  public static void main(String[] args) {
    String rootPath = "D:\\Workspaces\\webapps_github\\zxing-examples\\src\\main\\resources\\images\\";
    /*
     * String[] images = {"640_5.png", "640_6.png", "640_7.png", "640_8.png", "640_9.png",
     * "640_10.png", "640_11.png", "2_1.png", "2_2.png", "2_3.png", "2_4.png", "2_5.png",
     * "2_6.png"};
     */
//    String[] images = {"7.png", "8.png", "9.png", "10.png", "11.png"};
      String[] images = {"1_1.png", "1_2.png", "1_3.png", "1_4.png", "1_5.png", "1_6.png", "1_7.png",
          "1_8.png", "1_9.png", "1_10.png", "1_11.png"};
    for(int i = 0; i < images.length; i++) {
      deEncodeByPath(rootPath + images[i]);
    }
    
  }
}
