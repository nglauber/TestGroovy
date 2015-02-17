package br.com.nglauber.testgroovy
import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager
import com.squareup.otto.Bus

public class App extends Application {

    Bus bus

    @Override
    public void onCreate() {
        super.onCreate()

        FlowManager.init(this);

        bus = new Bus()
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }
}