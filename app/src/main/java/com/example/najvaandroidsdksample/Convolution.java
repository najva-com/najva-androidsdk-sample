package com.example.najvaandroidsdksample;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;

class Convolution extends AsyncTask<Bitmap, Void, Bitmap> {

    private OnBitmapReadyListener listener;

    public Convolution(OnBitmapReadyListener listener) {

        this.listener = listener;
    }

    // matrix to blur image
    int[][] matrix = {{1, 4, 6, 4, 1},
            {4, 16, 24, 16, 4},
            {6, 24, 36, 24, 6},
            {4, 16, 24, 16, 1},
            {1, 4, 6, 4, 1}};
    // kernel_width x kernel_height = dimension pf matrix
    // halfWidth = (kernel_width - 1)/2
    // halfHeight = (kernel_height - 1)/2
    int kernel_width = 5;
    int kernel_height = 5;
    int kernel_halfWidth = 2;
    int kernel_halfHeight = 2;

    public Bitmap convBitmap(Bitmap src) {

        int[][] sourceMatrix = new int[kernel_width][kernel_height];

        // averageWeight = total of matrix[][]. The result of each
        // pixel will be divided by averageWeight to get the average
        float averageWeight = 256;

        Bitmap scaledBitmap;
        if (src.getWidth()>200) {
            float scale = ((float) src.getWidth()) / 200f;


            scaledBitmap = Bitmap.createScaledBitmap(src, 200, (int) (src.getHeight() / scale), true);
            src.recycle();
        } else {
            scaledBitmap = src;
        }

        int pixelR, pixelG, pixelB, pixelA;

        int w = scaledBitmap.getWidth();
        int h = scaledBitmap.getHeight();
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        int step = 2;

        // fill sourceMatrix with surrounding pixel
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                for (int xk = 0; xk < kernel_width; xk++) {
                    for (int yk = 0; yk < kernel_height; yk++) {

                        int px = x + xk - kernel_halfWidth;
                        int py = y + yk - kernel_halfHeight;

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

                        sourceMatrix[xk][yk] = scaledBitmap.getPixel(px, py);

                    }
                }

                pixelR = pixelG = pixelB = pixelA = 0;

                for (int k = 0; k < kernel_width; k++) {
                    for (int l = 0; l < kernel_height; l++) {

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
                pixelR = (int) (pixelR / averageWeight);
                pixelG = (int) (pixelG / averageWeight);
                pixelB = (int) (pixelB / averageWeight);
                pixelA = (int) (pixelA / averageWeight);

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

        scaledBitmap.recycle();

        return bm;
    }

    @Override
    protected Bitmap doInBackground(Bitmap... bitmaps) {
        if (bitmaps.length == 0) return null;
        return convBitmap(bitmaps[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null)
            listener.onBitmapReady(bitmap);
    }

    interface OnBitmapReadyListener {
        void onBitmapReady(Bitmap bitmap);
    }
}