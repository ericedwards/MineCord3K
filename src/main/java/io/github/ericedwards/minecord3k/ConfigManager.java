/*
 * Copyright (c) 2021  Eric A Edwards
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.ericedwards.minecord3k;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager theManager = null;
    private static Logger logger = LogManager.getLogger();

    private static final String PROPERTIES_FILE_NAME = "/local.properties";

    private Properties props;

    private ConfigManager() {
        props = loadProperties();
    }

    public static synchronized ConfigManager instance() {
        if (theManager == null) {
            theManager = new ConfigManager();
            logger.info("new manager");
        }
        return theManager;
    }

    private static Properties loadProperties() {
        Properties classpathProps = new Properties();
        logger.debug("trying classpath");
        try {
            InputStream is = ConfigManager.class.getResourceAsStream(PROPERTIES_FILE_NAME);
            if (is == null) {
                logger.error("input stream is null");
            }
            classpathProps.load(is);
            is.close();
        } catch (IOException ioe) {
            logger.error("cannot load properties: " + ioe.toString());
        }
        logger.debug("loaded");
        return classpathProps;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public boolean isPropertyTrue(String key) {
        String s = props.getProperty(key);
        if (s == null) {
            return false;
        } else if (s.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public int getIntegerProperty(String key) {
        int value = 0;
        String s = props.getProperty(key);
        if (s != null) {
            try {
                value = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                // ignore and return 0
            }
        }
        return value;
    }

    public long getLongProperty(String key) {
        long value = 0;
        String s = props.getProperty(key);
        if (s != null) {
            try {
                value = Long.parseLong(s);
            } catch (NumberFormatException nfe) {
                // ignore and return 0
            }
        }
        return value;
    }

}
