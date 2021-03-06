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
package com.greatmancode.tools.events.interfaces;

/**
 * Interface for events that can be cancelled, to prevent them from propagating to downstream handlers.
 */
public interface Cancellable {
    /**
     * If an event stops propagating (ie, is cancelled) partway through an even
     * slot, that slot will not cease execution, but future even slots will not
     * be called.
     *
     * @param cancelled True to set event canceled, False to uncancel event.
     */
    void setCancelled(boolean cancelled);

    /**
     * Get event canceled state.
     *
     * @return whether event is cancelled
     */
    boolean isCancelled();
}
