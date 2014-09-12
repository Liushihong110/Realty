package com.realty.base.utils;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageUtils {
	 /**
     * ���ֳ�����ͼƬ��ʽ
     */
    public static String IMAGE_TYPE_GIF = "gif";// ͼ�ν�����ʽ
    public static String IMAGE_TYPE_JPG = "jpg";// ������Ƭר����
    public static String IMAGE_TYPE_JPEG = "jpeg";// ������Ƭר����
    public static String IMAGE_TYPE_BMP = "bmp";// Ӣ��Bitmap��λͼ���ļ�д������Windows����ϵͳ�еı�׼ͼ���ļ���ʽ
    public static String IMAGE_TYPE_PNG = "png";// ����ֲ����ͼ��
    public static String IMAGE_TYPE_PSD = "psd";// Photoshop��ר�ø�ʽPhotoshop
    /**
     * ����ͼ�񣨰��߶ȺͿ�����ţ�
     * @param srcImageFile Դͼ���ļ���ַ
     * @param result ���ź��ͼ���ַ
     * @param height ���ź�ĸ߶�
     * @param width ���ź�Ŀ��
     * @param bb ��������ʱ�Ƿ���Ҫ���ף�trueΪ����; falseΪ������;
     */
    public final static void scale(String srcImageFile, String result) {
    	int height=180;
    	int width=140;
    	boolean bb=true;
        try {
            double ratio = 0.0; // ���ű���
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // �������
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform
                        .getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//����
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ��ͼƬ�������ˮӡ
     * @param pressText ˮӡ����
     * @param srcImageFile Դͼ���ַ
     * @param destImageFile Ŀ��ͼ���ַ
     * @param fontName ˮӡ����������
     * @param fontStyle ˮӡ��������ʽ
     * @param color ˮӡ��������ɫ
     * @param fontSize ˮӡ�������С
     * @param x ����ֵ
     * @param y ����ֵ
     * @param alpha ͸���ȣ�alpha �����Ƿ�Χ [0.0, 1.0] ֮�ڣ������߽�ֵ����һ����������
     */
    public final static void pressText(
            String srcImageFile, String destImageFile) {
    	String pressText="��������";
    	String fontName="����";
        int fontStyle=Font.BOLD;
        Color color=Color.white;
        int fontSize=40;
        int x=0;
        int y=0;
        float alpha=0.5f;
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // ��ָ���������ˮӡ����
            g.drawString(pressText, (width - (getLength(pressText) * fontSize))
                    / 2 + x, (height - fontSize) / 2 + y);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// ������ļ���
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * ��ͼƬ���ͼƬˮӡ
     * @param pressImg ˮӡͼƬ
     * @param srcImageFile Դͼ���ַ
     * @param destImageFile Ŀ��ͼ���ַ
     * @param x ����ֵ�� Ĭ�����м�
     * @param y ����ֵ�� Ĭ�����м�
     * @param alpha ͸���ȣ�alpha �����Ƿ�Χ [0.0, 1.0] ֮�ڣ������߽�ֵ����һ����������
     */
    public final static void pressImage(String pressImg, String srcImageFile,String destImageFile,
            int x, int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            // ˮӡ�ļ�
            Image src_biao = ImageIO.read(new File(pressImg));
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            g.drawImage(src_biao, (wideth - wideth_biao) / 2,
                    (height - height_biao) / 2, wideth_biao, height_biao, null);
            // ˮӡ�ļ�����
            g.dispose();
            ImageIO.write((BufferedImage) image,  "JPEG", new File(destImageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * ����text�ĳ��ȣ�һ�������������ַ���
     * @param text
     * @return
     */
    public final static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }
}
