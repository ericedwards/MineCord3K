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
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class JavacordManager {

    private static final Logger logger = LogManager.getLogger();
    private static JavacordManager theManager = null;
    private DiscordApi discordApi = null;

    private JavacordManager() {
        start();
    }

    public static synchronized JavacordManager instance() {
        if (theManager == null) {
            theManager = new JavacordManager();
            logger.info("new manager");
        }
        return theManager;
    }

    public synchronized void start() {
        String token = "DISCORD_TOKEN_HERE";
        discordApi = new DiscordApiBuilder()
                .setToken(token)
                .login().join();
        discordApi.addMessageCreateListener(new DiscordBotDispatcher());
    }

    public synchronized void disconnect() {
        if (discordApi != null) {
            discordApi.disconnect();
        }
        discordApi = null;
    }

    public synchronized DiscordApi getDiscordApi() {
        return discordApi;
    }

}
