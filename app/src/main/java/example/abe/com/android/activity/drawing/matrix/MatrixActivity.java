package example.abe.com.android.activity.drawing.matrix;

import android.graphics.Matrix;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class MatrixActivity extends BaseActivity {

    @BindView(R.id.act_matrix_matrix_view)
    protected MatrixImageView matrixView;

    @Override
    public int getLayoutID() {
        return R.layout.activity_matrix;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_matrix_btn_translate)
    public void onBtnClick() {
        Matrix matrix = new Matrix();
//        // 1. 平移
//        matrix.postTranslate(matrixView.getImageBitmap().getWidth(), matrixView.getImageBitmap().getHeight());
//        // 在x方向平移view.getImageBitmap().getWidth()，在y轴方向view.getImageBitmap().getHeight()
//        matrixView.setImageMatrix(matrix);
//        // 下面的代码是为了查看matrix中的元素
//        float[] matrixValues = new float[9];
//        matrix.getValues(matrixValues);
//        for (int i = 0; i < 3; ++i) {
//            String temp = new String();
//            for (int j = 0; j < 3; ++j) {
//                temp += matrixValues[3 * i + j] + "\t";
//            }
//            LogUtil.e(temp);
//        }
//
//          // 2. 旋转(围绕图像的中心点)
//          matrix.setRotate(45f, view.getImageBitmap().getWidth() / 2f, view.getImageBitmap().getHeight() / 2f);
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth() * 1.5f, 0f);
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }


//          // 3. 旋转(围绕坐标原点) + 平移(效果同2)
//          matrix.setRotate(45f);
//          matrix.preTranslate(-1f * view.getImageBitmap().getWidth() / 2f, -1f * view.getImageBitmap().getHeight() / 2f);
//          matrix.postTranslate((float)view.getImageBitmap().getWidth() / 2f, (float)view.getImageBitmap().getHeight() / 2f);
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate((float)view.getImageBitmap().getWidth() * 1.5f, 0f);
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }

          // 4. 缩放
          matrix.setScale(2f, 2f);
          // 下面的代码是为了查看matrix中的元素
          float[] matrixValues = new float[9];
          matrix.getValues(matrixValues);
          for(int i = 0; i < 3; ++i)
          {
              String temp = new String();
              for(int j = 0; j < 3; ++j)
              {
                  temp += matrixValues[3 * i + j ] + "\t";
              }
              LogUtil.e(temp);
          }

          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
          matrix.postTranslate(matrixView.getImageBitmap().getWidth(), matrixView.getImageBitmap().getHeight());
          matrixView.setImageMatrix(matrix);

          // 下面的代码是为了查看matrix中的元素
          matrixValues = new float[9];
          matrix.getValues(matrixValues);
          for(int i = 0; i < 3; ++i)
          {
              String temp = new String();
              for(int j = 0; j < 3; ++j)
              {
                  temp += matrixValues[3 * i + j ] + "\t";
              }
              LogUtil.e(temp);
          }


//          // 5. 错切 - 水平
//          matrix.setSkew(0.5f, 0f);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth(), 0f);
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }

//          // 6. 错切 - 垂直
//          matrix.setSkew(0f, 0.5f);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight());
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }

//          7. 错切 - 水平 + 垂直
//          matrix.setSkew(0.5f, 0.5f);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight());
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }

//          // 8. 对称 (水平对称)
//          float matrix_values[] = {1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight() * 2f);
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }

//          // 9. 对称 - 垂直
//          float matrix_values[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth() * 2f, 0f);
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }


//          // 10. 对称(对称轴为直线y = x)
//          float matrix_values[] = {0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth(),
//                  view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth());
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              LogUtil.e(temp);
//          }
        matrixView.invalidate();
    }
}
