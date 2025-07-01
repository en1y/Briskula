# Creating an Executable with Embedded JRE

This README provides instructions for creating a standalone Windows executable (.exe) file for the Briskula game with an embedded JRE, so end users don't need to install Java.

## Prerequisites

1. **JDK 14 or later**: The `jpackage` tool is required and is included in JDK 14+.
   - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/)
   - Ensure the JDK bin directory is in your PATH

2. **Windows-specific requirements** (if building on Windows):
   - WiX Toolset 3.0 or later: Required for creating .exe and .msi installers
   - Download from: [WiX Toolset](https://wixtoolset.org/releases/)
   - Add WiX to your PATH

## Creating the Executable

### Option 1: Using the provided script

1. Make sure you have JDK 14+ installed and `jpackage` is available in your PATH
2. Navigate to the ConsoleGame directory
3. Run the script:
   ```
   ./output/create-exe-command.sh
   ```
4. The executable will be created in the `output/exe` directory

### Option 2: Manual command execution

If you prefer to run the command manually or need to customize it:

```bash
# Navigate to the ConsoleGameOld directory
cd /path/to/ConsoleGameOld

# Run jpackage
jpackage \
  --type exe \
  --input ./output/jar/ \
  --main-jar briskula-1.0.jar \
  --main-class App \
  --name briskula-1.0 \
  --dest ./output/exe \
  --runtime-image ./jre
```

## Additional Options

You can customize the executable by adding more options to the jpackage command:

- `--icon <path>`: Specify an icon for the application
- `--app-version <version>`: Set the application version
- `--vendor <vendor>`: Set the application vendor
- `--copyright <copyright>`: Set copyright information
- `--description <description>`: Add a description

For a complete list of options, run `jpackage --help`

## Troubleshooting

1. **jpackage command not found**: Ensure JDK 14+ is installed and its bin directory is in your PATH
2. **WiX Toolset errors**: Make sure WiX is properly installed and in your PATH (Windows only)
3. **Invalid runtime image**: Ensure the JRE directory structure is correct and contains all required files