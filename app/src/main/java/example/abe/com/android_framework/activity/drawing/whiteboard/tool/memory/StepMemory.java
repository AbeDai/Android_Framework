package example.abe.com.android_framework.activity.drawing.whiteboard.tool.memory;

import java.util.Iterator;
import java.util.Stack;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.IStep;

/**
 * Created by abe on 16/8/16.
 */
public class StepMemory {

    private Stack<IStep> mStackSaveStep;
    private Stack<IStep> mStackDeleteStep;

    public StepMemory(){
        initData();
    }

    private void initData() {
        mStackSaveStep = new Stack<>();
        mStackDeleteStep = new Stack<>();
    }

    public void saveStep(IStep step){
        mStackSaveStep.push(step);
    }

    public void restore(){
        if (!mStackSaveStep.isEmpty()){
            IStep step = mStackSaveStep.pop();
            mStackDeleteStep.push(step);
        }
    }

    public void unRestore(){
        if (!mStackDeleteStep.isEmpty()){
            IStep step = mStackDeleteStep.pop();
            mStackSaveStep.push(step);
        }
    }

    public void clearDeleteStep(){
        mStackDeleteStep.clear();
    }

    public Iterator<IStep> getSaveStepIterator() {
        return mStackSaveStep.iterator();
    }

}
