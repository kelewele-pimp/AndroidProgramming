package CustomViews;

import com.example.anothertodolistactivity.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class ToDoListItemView extends TextView{
	
	private Paint marginPaint; 
	private Paint linePaint; 
	private int paperColor; 
	private float margin; 

	public ToDoListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ToDoListItemView(Context context) {
		super(context);
		init();
	}

	public ToDoListItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init(){
		//Get reference to the resource table
		Resources myResources = getResources(); 
		
		//Create paint brushes that will be used in the onDraw method
		marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		marginPaint.setColor(myResources.getColor(R.color.notepad_margin));
		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG); 
		linePaint.setColor(myResources.getColor(R.color.notepad_lines));
		
		//Get the paper background color and the margin width
		paperColor = myResources.getColor(R.color.notepad_paper); 
		margin = myResources.getDimension(R.dimen.notepad_margin);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(paperColor);
		
		//Draw ruled lines 
		canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
		canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);
		
		//Draw margin
		canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);
		
		//Move the text accros from the margin
		canvas.save(); 
		canvas.translate(margin, 0);
		
		//use the Textview to render the text 
		super.onDraw(canvas);
		canvas.restore();
	}
	
	
	

}
