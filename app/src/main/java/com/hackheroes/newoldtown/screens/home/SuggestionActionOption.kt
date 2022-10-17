package com.hackheroes.newoldtown.screens.home

enum class SuggestionActionOption(val title: String) {
    EditSuggestion("Edit suggestion"),
    DeleteSuggestion("Delete suggestion");

    companion object {
        fun getByTitle(title: String): SuggestionActionOption {
            values().forEach { action ->
                if (title == action.title) return action
            }

            return EditSuggestion
        }

       fun getOptions(hasEditOption: Boolean): List<String> {
            val options = mutableListOf<String>()
            values().forEach { suggestionAction ->
                if (hasEditOption || suggestionAction != EditSuggestion) {
                    options.add(suggestionAction.title)
                }
            }
            return options
        }
    }
}
