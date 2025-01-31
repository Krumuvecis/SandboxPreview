package rotors.habitatEditor.window;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.modularHabitat.HabitatModule;
import rotors.modularHabitat.particularModules.SampleModule_green;

//
public final class UserData {
    private int @Nullable [] activeCellLocation; // {section, x, y}
    private final @NotNull EditModeData editModeData;

    //
    UserData() {
        activeCellLocation = null;
        editModeData = new EditModeData();
    }

    //
    public int @Nullable [] getActiveCellLocation() {
        return activeCellLocation;
    }

    //
    public void resetActiveCellLocation() {
        activeCellLocation = null;
    }

    //
    public void setActiveCellLocation(int sectionIndex, int lengthIndex, int widthIndex) {
        activeCellLocation = new int[] {sectionIndex, lengthIndex, widthIndex};
    }

    //
    public @NotNull EditModeData getEditModeData() {
        return editModeData;
    }

    //
    public static final class EditModeData {
        private static final @NotNull EditMode DEFAULT_EDIT_MODE = EditMode.EDIT_STRUCTURE;
        private static final @Nullable HabitatModule DEFAULT_TEMPLATE_MODULE = new SampleModule_green();
        private @NotNull EditMode editMode;
        private @Nullable HabitatModule templateModule; //for editing modules

        //
        EditModeData() {
            editMode = DEFAULT_EDIT_MODE;
            templateModule = DEFAULT_TEMPLATE_MODULE;
        }

        //
        public @NotNull EditMode getEditMode() {
            return editMode;
        }

        //
        public void setEditMode(@NotNull EditMode editMode) {
            this.editMode = editMode;
        }

        //
        public @Nullable HabitatModule getModuleByTemplate() {
            if (templateModule == null) {
                return null;
            } else {
                return templateModule.copy();
            }
        }

        //
        public void setTemplateModule(@Nullable HabitatModule module) {
            templateModule = module;
        }
    }

    //
    public enum EditMode {
        EDIT_SECTIONS,
        EDIT_STRUCTURE,
        EDIT_MODULES;
    }
}