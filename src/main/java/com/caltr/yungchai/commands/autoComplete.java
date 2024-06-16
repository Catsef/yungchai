package com.caltr.yungchai.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class autoComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> autocompleteOptions = new ArrayList<>();
        if (strings.length == 1) {
            autocompleteOptions.add("swordman_sword");
            autocompleteOptions.add("swordman_shield");
            autocompleteOptions.add("hunter_bow");
            autocompleteOptions.add("hunter_axe");
            autocompleteOptions.add("brawler_glove");
            autocompleteOptions.add("brawler_chain");
            return autocompleteOptions;
        }

        return autocompleteOptions;
    }
}
