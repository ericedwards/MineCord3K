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
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class MineCord3KPlugin extends JavaPlugin {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void onEnable() {
        logger.info("Enabling MineCord3K Plugin");
        JavacordManager javacordManager = JavacordManager.instance();
        this.getCommand("kit").setExecutor(new MinecraftKitCommand());
        this.getCommand("dbc").setExecutor(new MinecraftDCBCommand());
        this.getServer().getPluginManager().registerEvents(new MinecraftPlayerEventHandler(), this);
        String ip = IPAddressQuery.getIPAddressString();
        int port = this.getServer().getPort();
        if ((ip != null) && (port > 0)) {
            DiscordBotMessage.send("Minecraft server live: " + ip + ":" + port);
        }
    }

    @Override
    public void onDisable() {
        logger.info("Disabling MineCord3K Plugin");
        DiscordBotMessage.send("Minecraft server shutting down");
        JavacordManager.instance().disconnect();
    }
}
