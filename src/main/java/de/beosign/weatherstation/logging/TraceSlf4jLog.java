package de.beosign.weatherstation.logging;

import com.ecyrd.speed4j.StopWatch;
import com.ecyrd.speed4j.log.Slf4jLog;

/**
 * Extends the default class such that performance logging is done at TRACE level.
 * 
 * @author Florian Dahlmanns
 */
public class TraceSlf4jLog extends Slf4jLog {

    /**
     * Logs using the TRACE level.
     * 
     * @param sw stopwatch
     */
    @Override
    public void log(StopWatch sw) {
        if (m_log.isTraceEnabled()) {
            m_log.trace(sw.toString());
        }
    }
}
