/**
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2015, Greatman <http://github.com/greatman/>
 *
 * GreatmancodeTools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GreatmancodeTools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GreatmancodeTools.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.tools.interfaces;

import com.greatmancode.tools.ServerType;
import com.greatmancode.tools.caller.canary.CanaryServerCaller;
import com.greatmancode.tools.commands.canary.CanaryCommandReceiver;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.events.EventManager;
import lombok.Getter;
import lombok.Setter;
import net.canarymod.plugin.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class CanaryLoader extends Plugin implements Loader {
    private Common common;
    private EventManager eventManager;
    @Getter
    @Setter
    private CommandReceiver commandReceiver;

    @Override
    public void onEnable() {
        //Not used
    }

    @Override
    public void onDisable() {
        //Not used
    }

    @Override
    public ServerType getServerType() {
        return ServerType.CANARY;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public Common getCommon() {
        return common;
    }

    @Override
    public boolean enable() {

        CanaryServerCaller canaryCaller = new CanaryServerCaller(this);
        eventManager = new EventManager(canaryCaller);
        InputStream is = this.getClass().getResourceAsStream("/loader.yml");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            String mainClass = br.readLine();
            mainClass = mainClass.split("main-class:")[1].trim();

            Class<?> clazz = Class.forName(mainClass);

            if (Common.class.isAssignableFrom(clazz)) {
                common = (Common) clazz.newInstance();
                common.onEnable(canaryCaller, Logger.getLogger(getName()));
                return true;
            } else {
                this.getLogman().error("The class " + mainClass + " is invalid!");
                return false;
            }
        } catch (IOException e) {
            this.getLogman().error("Unable to load the main class!", e);
        } catch (ClassNotFoundException e) {
            this.getLogman().error("Unable to load the main class!", e);
            return false;
        } catch (InstantiationException e) {
            this.getLogman().error("Unable to load the main class!", e);
            return false;
        } catch (IllegalAccessException e) {
            this.getLogman().error("Unable to load the main class!", e);
            return false;
        }
        return false;
    }

    @Override
    public void disable() {

    }
}
