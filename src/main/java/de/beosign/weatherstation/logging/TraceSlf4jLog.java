package de.beosign.weatherstation.logging;

import com.ecyrd.speed4j.StopWatch;
import com.ecyrd.speed4j.log.Slf4jLog;

public class TraceSlf4jLog extends Slf4jLog {

    /**
     * Logs using the TRACE level.
     */
    @Override
    public void log(StopWatch sw) {
        if (m_log.isTraceEnabled()) {
            m_log.trace(sw.toString());
        }
    }
}
