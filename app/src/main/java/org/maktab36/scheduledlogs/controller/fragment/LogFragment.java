package org.maktab36.scheduledlogs.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.maktab36.scheduledlogs.R;
import org.maktab36.scheduledlogs.model.Log;
import org.maktab36.scheduledlogs.repository.LogRepository;
import org.maktab36.scheduledlogs.worker.LogWorker;

import java.util.List;

public class LogFragment extends Fragment {
    private Button mStartButton;
    private Button mStopButton;
    private TextView mTextViewLog;
    private LogRepository mRepository;
    private List<Log> mLogList;

    public LogFragment() {
        // Required empty public constructor
    }

    public static LogFragment newInstance() {
        LogFragment fragment = new LogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository=LogRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);
        findViews(view);
        setListeners();

        initTextView();


        mRepository.getLogsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Log>>() {
            @Override
            public void onChanged(List<Log> logs) {
                android.util.Log.d("LST", "onChanged:");
                if(mLogList!=null&&mLogList.size()!=0){
                    for (Log log : logs) {
                        if(!mLogList.contains(log)){
                            mLogList.add(log);
                            mTextViewLog.append(log.getLogMessage());
                        }
                    }
                }else{
                    mLogList=logs;
                }
            }
        });

        return view;
    }

    private void initTextView() {
        mLogList=mRepository.getLogsLiveData().getValue();
        if(mLogList!=null&&mLogList.size()!=0){
            for (Log log:mLogList) {
                mTextViewLog.append(log.getLogMessage());
            }
        }
    }

    private void findViews(View view) {
        mStartButton=view.findViewById(R.id.button_start_scheduling);
        mStopButton=view.findViewById(R.id.button_stop_scheduling);
        mTextViewLog=view.findViewById(R.id.text_view_log);
    }

    private void setListeners(){
        mStartButton.setOnClickListener(view -> LogWorker.ScheduleWork(getContext()));

        mStopButton.setOnClickListener(view -> LogWorker.cancelWork(getContext()));
    }
}