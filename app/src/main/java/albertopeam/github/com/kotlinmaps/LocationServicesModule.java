//package albertopeam.github.com.kotlinmaps;
//
//import android.os.HandlerThread;
//import android.os.Looper;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import es.hiro.HiroApp;
//import es.hiro.app.LocationClient;
//
///**
// * Created by albertopenasamor on 19/10/16.
// */
//
//public class LocationServicesModule {
//
//
//    public static final int TIMEOUT_TO_CONNECT_SECS = 5;
//    public static final int MAX_RETRIES = 20;
//    public static final int RETRY_TIME_MS = 250;
//
//    LocationClient provideGpsClient(HiroApp application){
//        HandlerThread handlerThread = new HandlerThread("LocationHandlerThread");
//        handlerThread.start();
//        // Now get the Looper from the HandlerThread
//        // NOTE: This call will block until the HandlerThread gets control and initializes its Looper
//        Looper looper = handlerThread.getLooper();
//        // Request location updates to be called back on the HandlerThread
//        return new LocationClientImpl(application, looper, TIMEOUT_TO_CONNECT_SECS, MAX_RETRIES, RETRY_TIME_MS);
//    }
//
//}