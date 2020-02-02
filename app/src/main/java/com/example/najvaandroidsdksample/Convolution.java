package com.example.najvaandroidsdksample;

import android.graphics.Bitmap;
import android.graphics.Color;

class Convolution {

    // matrix to blur image
    int[][] matrix = {{1, 4, 6, 4, 1},
            {4, 16, 24, 16, 4},
            {6, 24, 36, 24, 6},
            {4, 16, 24, 16, 1},
            {1, 4, 6, 4, 1}};
    // kernal_width x kernal_height = dimension pf matrix
    // halfWidth = (kernal_width - 1)/2
    // halfHeight = (kernal_height - 1)/2
    int kernal_width = 5;
    int kernal_height = 5;
    int kernal_halfWidth = 2;
    int kernal_halfHeight = 2;

    public Bitmap convBitmap(Bitmap src) {

        int[][] sourceMatrix = new int[kernal_width][kernal_height];

        // averageWeight = total of matrix[][]. The result of each
        // pixel will be divided by averageWeight to get the average
        float averageWeight = 1f / 256f;

        int pixelR, pixelG, pixelB, pixelA;

        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        // fill sourceMatrix with surrounding pixel
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                for (int xk = 0; xk < kernal_width; xk++) {
                    for (int yk = 0; yk < kernal_height; yk++) {
                        int px = x + xk - kernal_halfWidth;
                        int py = y + yk - kernal_halfHeight;

                        if (px < 0) {
                            px = 0;
                        } else if (px >= w) {
                            px = w - 1;
                        }

                        if (py < 0) {
                            py = 0;
                        } else if (py >= h) {
                            py = h - 1;
                        }

                        sourceMatrix[xk][yk] = src.getPixel(px, py);

                    }
                }

                pixelR = pixelG = pixelB = pixelA = 0;

                for (int k = 0; k < kernal_width; k++) {
                    for (int l = 0; l < kernal_height; l++) {

                        pixelR += Color.red(sourceMatrix[k][l])
                                * matrix[k][l];
                        pixelG += Color.green(sourceMatrix[k][l])
                                * matrix[k][l];
                        pixelB += Color.blue(sourceMatrix[k][l])
                                * matrix[k][l];
                        pixelA += Color.alpha(sourceMatrix[k][l])
                                * matrix[k][l];
                    }
                }
                pixelR = (int) (pixelR * averageWeight);
                pixelG = (int) (pixelG * averageWeight);
                pixelB = (int) (pixelB * averageWeight);
                pixelA = (int) (pixelA * averageWeight);

                if (pixelR < 0) {
                    pixelR = 0;
                } else if (pixelR > 255) {
                    pixelR = 255;
                }

                if (pixelG < 0) {
                    pixelG = 0;
                } else if (pixelG > 255) {
                    pixelG = 255;
                }

                if (pixelB < 0) {
                    pixelB = 0;
                } else if (pixelB > 255) {
                    pixelB = 255;
                }

                if (pixelA < 0) {
                    pixelA = 0;
                } else if (pixelA > 255) {
                    pixelA = 255;
                }

                bm.setPixel(x, y,
                        Color.argb(pixelA, pixelR, pixelG, pixelB));
            }
        }

        return bm;
    }
}