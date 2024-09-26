package kraylib.raylib.core

import kraylib.FFM
import kraylib.ffm.Raylib
import kraylib.FFM.arena
import kraylib.raylib.Pointer
import kraylib.raylib.collections.NativeList
import kraylib.raylib.ext.allocate
import kraylib.raylib.structs.*

object CameraMode {
    /** Custom camera */
    const val CAMERA_CUSTOM = 0
    /** Free Camera */
    const val CAMERA_FREE = 1
    /** Orbital camera */
    const val CAMERA_ORBITAL = 2
    /** First person camera */
    const val CAMERA_FIRST_PERSON = 3
    /** Third person camera */
    const val CAMERA_THIRD_PERSON = 4
}

object ConfigFlags {
    const val FLAG_VSYNC_HINT = 0x00000040                // Set to try enabling V-Sync on GPU
    const val FLAG_FULLSCREEN_MODE = 0x00000002           // Set to run program in fullscreen
    const val FLAG_WINDOW_RESIZABLE = 0x00000004          // Set to allow resizable window
    const val FLAG_WINDOW_UNDECORATED = 0x00000008        // Set to disable window decoration (frame and buttons)
    const val FLAG_WINDOW_HIDDEN = 0x00000080             // Set to hide window
    const val FLAG_WINDOW_MINIMIZED = 0x00000200          // Set to minimize window (iconify)
    const val FLAG_WINDOW_MAXIMIZED = 0x00000400          // Set to maximize window (expanded to monitor)
    const val FLAG_WINDOW_UNFOCUSED = 0x00000800          // Set to window non focused
    const val FLAG_WINDOW_TOPMOST = 0x00001000            // Set to window always on top
    const val FLAG_WINDOW_ALWAYS_RUN = 0x00000100         // Set to allow windows running while minimized
    const val FLAG_WINDOW_TRANSPARENT = 0x00000010        // Set to allow transparent framebuffer
    const val FLAG_WINDOW_HIGHDPI = 0x00002000            // Set to support HighDPI
    const val FLAG_WINDOW_MOUSE_PASSTHROUGH = 0x00004000  // Set to support mouse passthrough, only supported when FLAG_WINDOW_UNDECORATED
    const val FLAG_BORDERLESS_WINDOWED_MODE = 0x00008000  // Set to run program in borderless windowed mode
    const val FLAG_MSAA_4X_HINT = 0x00000020              // Set to try enabling MSAA 4X
    const val FLAG_INTERLACED_HINT = 0x00010000           // Set to try enabling interlaced video format (for V3D)
}

object Keys {
    /** Key: NULL, used for no key pressed */
    const val KEY_NULL = 0
    /**  Key: ' */
    const val KEY_APOSTROPHE = 39
    /**  Key: , */
	const val KEY_COMMA = 44
    /**  Key: - */
	const val KEY_MINUS = 45
    /**  Key: . */
	const val KEY_PERIOD = 46
    /**  Key: / */
	const val KEY_SLASH = 47
    /**  Key: 0 */
	const val KEY_ZERO = 48
    /**  Key: 1 */
	const val KEY_ONE = 49
    /**  Key: 2 */
	const val KEY_TWO = 50
    /**  Key: 3 */
	const val KEY_THREE = 51
    /**  Key: 4 */
	const val KEY_FOUR = 52
    /**  Key: 5 */
	const val KEY_FIVE = 53
    /**  Key: 6 */
	const val KEY_SIX = 54
    /**  Key: 7 */
	const val KEY_SEVEN = 55
    /**  Key: 8 */
	const val KEY_EIGHT = 56
    /**  Key: 9 */
	const val KEY_NINE = 57
    /**  Key:  */
	const val KEY_SEMICOLON = 59
    /**  Key: = */
	const val KEY_EQUAL = 61
    /**  Key: A | a */
	const val KEY_A = 65
    /**  Key: B | b */
	const val KEY_B = 66
    /**  Key: C | c */
	const val KEY_C = 67
    /**  Key: D | d */
	const val KEY_D = 68
    /**  Key: E | e */
	const val KEY_E = 69
    /**  Key: F | f */
	const val KEY_F = 70
    /**  Key: G | g */
	const val KEY_G = 71
    /**  Key: H | h */
	const val KEY_H = 72
    /**  Key: I | i */
	const val KEY_I = 73
    /**  Key: J | j */
	const val KEY_J = 74
    /**  Key: K | k */
	const val KEY_K = 75
    /**  Key: L | l */
	const val KEY_L = 76
    /**  Key: M | m */
	const val KEY_M = 77
    /**  Key: N | n */
	const val KEY_N = 78
    /**  Key: O | o */
	const val KEY_O = 79
    /**  Key: P | p */
	const val KEY_P = 80
    /**  Key: Q | q */
	const val KEY_Q = 81
    /**  Key: R | r */
	const val KEY_R = 82
    /**  Key: S | s */
	const val KEY_S = 83
    /**  Key: T | t */
	const val KEY_T = 84
    /**  Key: U | u */
	const val KEY_U = 85
    /**  Key: V | v */
	const val KEY_V = 86
    /**  Key: W | w */
	const val KEY_W = 87
    /**  Key: X | x */
	const val KEY_X = 88
    /**  Key: Y | y */
	const val KEY_Y = 89
    /**  Key: Z | z */
	const val KEY_Z = 90
    /**  Key: [ */
	const val KEY_LEFT_BRACKET = 91
    /**  Key: '\' */
	const val KEY_BACKSLASH = 92
    /**  Key: ] */
	const val KEY_RIGHT_BRACKET = 93
    /**  Key: ` */
	const val KEY_GRAVE = 96
    /**  Key: Space */
	const val KEY_SPACE = 32
    /**  Key: Esc */
	const val KEY_ESCAPE = 256
    /**  Key: Enter */
	const val KEY_ENTER = 257
    /**  Key: Tab */
	const val KEY_TAB = 258
    /**  Key: Backspace */
	const val KEY_BACKSPACE = 259
    /**  Key: Ins */
	const val KEY_INSERT = 260
    /**  Key: Del */
	const val KEY_DELETE = 261
    /**  Key: Cursor right */
	const val KEY_RIGHT = 262
    /**  Key: Cursor left */
	const val KEY_LEFT = 263
    /**  Key: Cursor down */
	const val KEY_DOWN = 264
    /**  Key: Cursor up */
	const val KEY_UP = 265
    /**  Key: Page up */
	const val KEY_PAGE_UP = 266
    /**  Key: Page down */
	const val KEY_PAGE_DOWN = 267
    /**  Key: Home */
	const val KEY_HOME = 268
    /**  Key: End */
	const val KEY_END = 269
    /**  Key: Caps lock */
	const val KEY_CAPS_LOCK = 280
    /**  Key: Scroll down */
	const val KEY_SCROLL_LOCK = 281
    /**  Key: Num lock */
	const val KEY_NUM_LOCK = 282
    /**  Key: Print screen */
	const val KEY_PRINT_SCREEN = 283
    /**  Key: Pause */
	const val KEY_PAUSE = 284
    /**  Key: F1 */
	const val KEY_F1 = 290
    /**  Key: F2 */
	const val KEY_F2 = 291
    /**  Key: F3 */
	const val KEY_F3 = 292
    /**  Key: F4 */
	const val KEY_F4 = 293
    /**  Key: F5 */
	const val KEY_F5 = 294
    /**  Key: F6 */
	const val KEY_F6 = 295
    /**  Key: F7 */
	const val KEY_F7 = 296
    /**  Key: F8 */
	const val KEY_F8 = 297
    /**  Key: F9 */
	const val KEY_F9 = 298
    /**  Key: F10 */
	const val KEY_F10 = 299
    /**  Key: F11 */
	const val KEY_F11 = 300
    /**  Key: F12 */
	const val KEY_F12 = 301
    /**  Key: Shift left */
	const val KEY_LEFT_SHIFT = 340
    /**  Key: Control left */
	const val KEY_LEFT_CONTROL = 341
    /**  Key: Alt left */
	const val KEY_LEFT_ALT = 342
    /**  Key: Super left */
	const val KEY_LEFT_SUPER = 343
    /**  Key: Shift right */
	const val KEY_RIGHT_SHIFT = 344
    /**  Key: Control right */
	const val KEY_RIGHT_CONTROL = 345
    /**  Key: Alt right */
	const val KEY_RIGHT_ALT = 346
    /**  Key: Super right */
	const val KEY_RIGHT_SUPER = 347
    /**  Key: KB menu */
	const val KEY_KB_MENU = 348
    /**  Key: Keypad 0 */
	const val KEY_KP_0 = 320
    /**  Key: Keypad 1 */
	const val KEY_KP_1 = 321
    /**  Key: Keypad 2 */
	const val KEY_KP_2 = 322
    /**  Key: Keypad 3 */
	const val KEY_KP_3 = 323
    /**  Key: Keypad 4 */
	const val KEY_KP_4 = 324
    /**  Key: Keypad 5 */
	const val KEY_KP_5 = 325
    /**  Key: Keypad 6 */
	const val KEY_KP_6 = 326
    /**  Key: Keypad 7 */
	const val KEY_KP_7 = 327
    /**  Key: Keypad 8 */
	const val KEY_KP_8 = 328
    /**  Key: Keypad 9 */
	const val KEY_KP_9 = 329
    /**  Key: Keypad . */
	const val KEY_KP_DECIMAL = 330
    /**  Key: Keypad / */
	const val KEY_KP_DIVIDE = 331
    /**  Key: Keypad * */
	const val KEY_KP_MULTIPLY = 332
    /**  Key: Keypad - */
	const val KEY_KP_SUBTRACT = 333
    /**  Key: Keypad + */
	const val KEY_KP_ADD = 334
    /**  Key: Keypad Enter */
	const val KEY_KP_ENTER = 335
    /**  Key: Keypad = */
	const val KEY_KP_EQUAL = 336
    /**  Key: Android back button */
	const val KEY_BACK = 4
    /**  Key: Android menu button */
	const val KEY_MENU = 82
    /**  Key: Android volume up button */
	const val KEY_VOLUME_UP = 24
    /** Key: Android volume down button */
    const val KEY_VOLUME_DOWN     = 25
}

object MouseButtons {
    // Mouse buttons
    /** Mouse button left */ const val MOUSE_BUTTON_LEFT = 0
    /** Mouse button right */ const val MOUSE_BUTTON_RIGHT = 1
    /** Mouse button middle (pressed wheel) */ const val MOUSE_BUTTON_MIDDLE = 2
    /** Mouse button side (advanced mouse device) */ const val MOUSE_BUTTON_SIDE = 3
    /** Mouse button extra (advanced mouse device) */ const val MOUSE_BUTTON_EXTRA = 4
    /** Mouse button forward (advanced mouse device) */ const val MOUSE_BUTTON_FORWARD = 5
    /** Mouse button back (advanced mouse device) */ const val MOUSE_BUTTON_BACK = 6
}

object MouseCursor {
    /** Default pointer shape */ const val MOUSE_CURSOR_DEFAULT = 0
    /** Arrow shape */ const val MOUSE_CURSOR_ARROW = 1
    /** Text writing cursor shape */ const val MOUSE_CURSOR_IBEAM = 2
    /** Cross shape */ const val MOUSE_CURSOR_CROSSHAIR = 3
    /** Pointing hand cursor */ const val MOUSE_CURSOR_POINTING_HAND = 4
    /** Horizontal resize/move arrow shape */ const val MOUSE_CURSOR_RESIZE_EW = 5
    /** Vertical resize/move arrow shape */ const val MOUSE_CURSOR_RESIZE_NS = 6
    /** Top-left to bottom-right diagonal resize/move arrow shape */ const val MOUSE_CURSOR_RESIZE_NWSE = 7
    /** The top-right to bottom-left diagonal resize/move arrow shape */ const val MOUSE_CURSOR_RESIZE_NESW = 8
    /** The omnidirectional resize/move cursor shape */ const val MOUSE_CURSOR_RESIZE_ALL = 9
    /** The operation-not-allowed shape */ const val MOUSE_CURSOR_NOT_ALLOWED = 10
}

object GamepadButtons {
    /** Unknown button, just for error checking */ const val GAMEPAD_BUTTON_UNKNOWN = 0
    /** Gamepad left DPAD up button */ const val GAMEPAD_BUTTON_LEFT_FACE_UP = 1
    /** Gamepad left DPAD right button */ const val GAMEPAD_BUTTON_LEFT_FACE_RIGHT = 2
    /** Gamepad left DPAD down button */ const val GAMEPAD_BUTTON_LEFT_FACE_DOWN = 3
    /** Gamepad left DPAD left button */ const val GAMEPAD_BUTTON_LEFT_FACE_LEFT = 4
    /** Gamepad right button up (i.e. PS3: Triangle, Xbox: Y) */ const val GAMEPAD_BUTTON_RIGHT_FACE_UP = 5
    /** Gamepad right button right (i.e. PS3: Square, Xbox: X) */ const val GAMEPAD_BUTTON_RIGHT_FACE_RIGHT = 6
    /** Gamepad right button down (i.e. PS3: Cross, Xbox: A) */ const val GAMEPAD_BUTTON_RIGHT_FACE_DOWN = 7
    /** Gamepad right button left (i.e. PS3: Circle, Xbox: B) */ const val GAMEPAD_BUTTON_RIGHT_FACE_LEFT = 8
    /** Gamepad top/back trigger left (first), it could be a trailing button */ const val GAMEPAD_BUTTON_LEFT_TRIGGER_1 = 9
    /** Gamepad top/back trigger left (second), it could be a trailing button */ const val GAMEPAD_BUTTON_LEFT_TRIGGER_2 = 10
    /** Gamepad top/back trigger right (one), it could be a trailing button */ const val GAMEPAD_BUTTON_RIGHT_TRIGGER_1 = 11
    /** Gamepad top/back trigger right (second), it could be a trailing button */ const val GAMEPAD_BUTTON_RIGHT_TRIGGER_2 = 12
    /** Gamepad center buttons, left one (i.e. PS3: Select) */ const val GAMEPAD_BUTTON_MIDDLE_LEFT = 13
    /** Gamepad center buttons, middle one (i.e. PS3: PS, Xbox: XBOX) */ const val GAMEPAD_BUTTON_MIDDLE = 14
    /** Gamepad center buttons, right one (i.e. PS3: Start) */ const val GAMEPAD_BUTTON_MIDDLE_RIGHT = 15
    /** Gamepad joystick pressed button left */ const val GAMEPAD_BUTTON_LEFT_THUMB = 16
    /** Gamepad joystick pressed button right */ const val GAMEPAD_BUTTON_RIGHT_THUMB = 17
}

object GamepadAxis {
    /** Gamepad left stick X axis */ const val GAMEPAD_AXIS_LEFT_X = 0
    /** Gamepad left stick Y axis */ const val GAMEPAD_AXIS_LEFT_Y = 1
    /** Gamepad right stick X axis */ const val GAMEPAD_AXIS_RIGHT_X = 2
    /** Gamepad right stick Y axis */ const val GAMEPAD_AXIS_RIGHT_Y = 3
    /** Gamepad back trigger left, pressure level: [1..-1] */ const val GAMEPAD_AXIS_LEFT_TRIGGER = 4
    /** Gamepad back trigger right, pressure level: [1..-1] */ const val GAMEPAD_AXIS_RIGHT_TRIGGER = 5
}

/** Setup canvas (framebuffer) to start drawing */
fun beginDrawing() = Raylib.BeginDrawing()

/** Begin 2D mode with custom camera (2D) */
fun beginMode(camera2D: Camera2D) = Raylib.BeginMode2D(camera2D.memorySegment)

/** Begin drawing to render texture */
fun beginTextureMode(renderTexture: RenderTexture) = Raylib.BeginTextureMode(renderTexture.memorySegment)

/** Set background color (framebuffer clear color) */
fun clearBackground(color: Color) = Raylib.ClearBackground(color.memorySegment)

/** Close window and unload OpenGL context */
fun closeWindow() = Raylib.CloseWindow()

/** End canvas drawing and swap buffers (double buffering) */
fun endDrawing() = Raylib.EndDrawing()

/** Ends 2D mode with custom camera */
fun endMode2D() = Raylib.EndMode2D()

/** Ends drawing to render texture */
fun endTextureMode() = Raylib.EndTextureMode()

/**
 * Initialize window and OpenGL context
 */
fun initWindow(width: Int, height: Int, title: String) = Raylib.InitWindow(width, height, arena.allocateFrom(title))

/**
 * Check if a key has been pressed once
 * @see kraylib.raylib.Keys
 */
fun isKeyPressed(key: Int) = Raylib.IsKeyPressed(key)
/**
 * Check if a key has been pressed again (Only PLATFORM_DESKTOP)
 * @see kraylib.raylib.Keys
 */
fun isKeyPressedRepeat(key: Int) = Raylib.IsKeyPressedRepeat(key)
/**
 * Check if a key is being pressed
 * @see kraylib.raylib.Keys
 */
fun isKeyDown(key: Int) = Raylib.IsKeyDown(key)
/**
 * Check if a key has been released once
 * @see kraylib.raylib.Keys
 */
fun isKeyReleased(key: Int) = Raylib.IsKeyReleased(key)
/**
 * Check if a key is NOT being pressed
 * @see kraylib.raylib.Keys
 */
fun isKeyUp(key: Int) = Raylib.IsKeyUp(key)
/**
 * Get key pressed (keycode), call it multiple times for keys queued, returns 0 when the queue is empty
 * @see kraylib.raylib.Keys
 */
fun getKeyPressed() = Raylib.GetKeyPressed()
/**
 * Get char pressed (unicode), call it multiple times for chars queued, returns 0 when the queue is empty
 * @see kraylib.raylib.Keys
 */
fun getCharPressed() = Raylib.GetCharPressed()
/**
 * Set a custom key to exit program (default is ESC)
 * @see kraylib.raylib.Keys
 */
fun setExitKey(key: Int) = Raylib.SetExitKey(key)

/** Check if a mouse button has been pressed once */
fun isMouseButtonPressed(button: Int) = Raylib.IsMouseButtonPressed(button)
/** Check if a mouse button is being pressed */
fun isMouseButtonDown(button: Int) = Raylib.IsMouseButtonDown(button)
/** Check if a mouse button has been released once */
fun isMouseButtonReleased(button: Int) = Raylib.IsMouseButtonReleased(button)
/** Check if a mouse button is NOT being pressed */
fun isMouseButtonUp(button: Int) = Raylib.IsMouseButtonUp(button)
/** Get mouse position X */
fun getMouseX() = Raylib.GetMouseX()
/** Get mouse position Y */
fun getMouseY() = Raylib.GetMouseY()
/** Get mouse position XY */
fun getMousePosition(): Vector2 {
    val segment = Raylib.GetMousePosition(arena)
    return Vector2(segment)
}
/** Get mouse delta between frames */
fun getMouseDelta(): Vector2 {
    val segment = Raylib.GetMouseDelta(arena)
    return Vector2(segment)
}
/** Set mouse position XY */
fun setMousePosition(x: Int, y: Int) = Raylib.SetMousePosition(x, y)
/** Set mouse offset */
fun setMouseOffset(offsetX: Int, offsetY: Int) = Raylib.SetMouseOffset(offsetX, offsetY)
/** Set mouse scaling */
fun setMouseScale(scaleX: Float, scaleY: Float) = Raylib.SetMouseScale(scaleX, scaleY)
/** Get mouse wheel movement for X or Y, whichever is larger */
fun getMouseWheelMove() = Raylib.GetMouseWheelMove()
/** Get mouse wheel movement for both X and Y */
fun getMouseWheelMoveV(): Vector2 {
    val segment = Raylib.GetMouseWheelMoveV(arena)
    return Vector2(segment)
}
/** Set mouse cursor */
fun setMouseCursor(cursor: Int) = Raylib.SetMouseCursor(cursor)

/** Check if window has been initialized successfully */
fun isWindowReady() = Raylib.IsWindowReady()
/** Check if window is currently fullscreen */
fun isWindowFullscreen() = Raylib.IsWindowFullscreen()
/** Check if window is currently hidden (only PLATFORM_DESKTOP) */
fun isWindowHidden() = Raylib.IsWindowHidden()
/** Check if window is currently minimized (only PLATFORM_DESKTOP) */
fun isWindowMinimized() = Raylib.IsWindowMinimized()
/** Check if window is currently maximized (only PLATFORM_DESKTOP) */
fun isWindowMaximized() = Raylib.IsWindowMaximized()
/** Check if window is currently focused (only PLATFORM_DESKTOP) */
fun isWindowFocused() = Raylib.IsWindowFocused()
/** Check if window has been resized last frame */
fun isWindowResized() = Raylib.IsWindowResized()
/**
 * Check if one specific window flag is enabled
 * @see kraylib.raylib.ConfigFlags
 */
fun isWindowState(flag: Int) = Raylib.IsWindowState(flag)
/** Set window dimensions */
fun setWindowSize(width: Int, height: Int) = Raylib.SetWindowSize(width, height)
/**
 * Set window configuration state using flags (only PLATFORM_DESKTOP)
 * @see kraylib.raylib.ConfigFlags
 */
fun setWindowState(flag: Int) = Raylib.SetWindowState(flag)
/**
 * Clear window configuration state flags
 * @see kraylib.raylib.ConfigFlags
 */
fun clearWindowState(flag: Int) = Raylib.ClearWindowState(flag)
/**
 * Setup init configuration flags (view FLAGS)
 * @see kraylib.raylib.ConfigFlags
 */
fun setConfigFlags(flags: Int) = Raylib.SetConfigFlags(flags)

/** Get native window handle */
fun getWindowHandle() = Raylib.GetWindowHandle()
/** Get current screen width */
fun getScreenWidth() = Raylib.GetScreenWidth()
/** Get current screen height */
fun getScreenHeight() = Raylib.GetScreenHeight()
/** Get current render width (it considers HiDPI) */
fun getRenderWidth() = Raylib.GetRenderWidth()
/** Get current render height (it considers HiDPI) */
fun getRenderHeight() = Raylib.GetRenderHeight()
/** Get number of connected monitors */
fun getMonitorCount() = Raylib.GetMonitorCount()
/** Get current connected monitor */
fun getCurrentMonitor() = Raylib.GetCurrentMonitor()

/** Check if application should close (KEY_ESCAPE pressed or windows close icon clicked) */
fun windowShouldClose() = Raylib.WindowShouldClose()

//------------------------------------------------------------------------------------
// Font Loading and Text Drawing Functions (Module: text)
//------------------------------------------------------------------------------------

fun test() = Raylib.LoadFontFromMemory()

// Font loading/unloading functions
fun getFontDefault() = Font(Raylib.GetFontDefault(arena))                                                        // Get the default Font
fun loadFont(fileName: String) = Font(Raylib.LoadFont(arena, fileName.allocate(arena)))                                               // Load font from file into GPU memory (VRAM)
fun loadFontEx(fileName: String, fontSize: Int, int *codepoints, codepointCount: Int): Font  {} // Load font from file with extended parameters, use NULL for codepoints and 0 for codepointCount to load the default character set
fun loadFontFromImage(image: Image, key: Color, firstChar: Int) = Font(Raylib.LoadFontFromImage(arena, image.memorySegment, key.memorySegment, firstChar))                       // Load font from Image (XNA style)
fun loadFontFromMemory(fileType: String, fileData: Pointer<UByte>, dataSize: Int, fontSize: Int, int *codepoints, codepointCount: Int) = Rayl // Load font from memory buffer, fileType refers to extension: i.e. '.ttf'
fun isFontReady(font: Font) = Raylib.IsFontReady(font.memorySegment)                                                        // Check if a font is ready
fun *loadFontData(const unsigned char *fileData, dataSize: Int, fontSize: Int, int *codepoints, codepointCount: Int, type: Int): GlyphInfo  {}// Load font data for further use
fun genImageFontAtlas(const GlyphInfo *glyphs, Rectangle **glyphRecs, glyphCount: Int, fontSize: Int, padding: Int, packMethod: Int): Image  {}// Generate image font atlas using chars info
fun unloadFontData(glyphs: NativeList<GlyphInfo>, glyphCount: Int) = Raylib.UnloadFontData(glyphs.memorySegment, glyphCount)                             // Unload font chars info data (RAM)
fun unloadFont(font: Font) = Raylib.UnloadFont(font.memorySegment)                                                          // Unload font from GPU memory (VRAM)
fun exportFontAsCode(font: Font, fileName: String) = Raylib.ExportFontAsCode(font.memorySegment, fileName.allocate(arena))                             // Export font as code file, returns true on success

// Text drawing functions
fun drawFPS(posX: Int, posY: Int): Unit = Raylib.DrawFPS(posX, posY)                                                    // Draw current FPS
fun drawText(text: String, posX: Int, posY: Int, fontSize: Int, color: Color) = Raylib.DrawText(arena.allocateFrom(text), posX, posY, fontSize, color.memorySegment)  // Draw text (using default font)
fun drawTextEx(font: Font, text: String, position: Vector2, fontSize: Float, spacing: Float, tint: Color) = Raylib.DrawTextEx(
    font, arena.allocateFrom(text), position.memorySegment, fontSize, spacing, tint.memorySegment
)// Draw text using font and additional parameters
fun drawTextPro(font: Font, text: String, position: Vector2, origin: Vector2, rotation: Float, fontSize: Float, spacing: Float, tint: Color) = Raylib.DrawTextPro(
    font, text.allocate(arena), position.memorySegment, origin.memorySegment, rotation, fontSize, spacing, tint.memorySegment
)// Draw text using Font and pro parameters (rotation)
fun drawTextCodepoint(font: Font, codepoint: Int, position: Vector2, fontSize: Float, tint: Color) = Raylib.DrawTextCodepoint(
    font, codepoint, position.memorySegment, fontSize, tint.memorySegment
) // Draw one character (codepoint)
fun drawTextCodepoints(font: Font, const int *codepoints, codepointCount: Int, position: Vector2, fontSize: Float, spacing: Float, tint: Color): Unit  {}// Draw multiple character (codepoint)

// Text font info functions
fun setTextLineSpacing(spacing: Int) = Raylib.SetTextLineSpacing(spacing)                                                // Set vertical line spacing when drawing with line-breaks
fun measureText(text: String, fontSize: Int) = Raylib.MeasureText(text.allocate(arena), fontSize)                                    // Measure string width for default font
fun measureTextEx(font: Font, text: String, fontSize: Float, spacing: Float): Vector2 = Raylib.MeasureTextEx(
    arena, font, text.allocate(arena), fontSize, spacing
)  // Measure string size for Font
fun getGlyphIndex(font: Font, codepoint: Int) = Raylib.GetGlyphIndex(font, codepoint)                                         // Get glyph index position in font for a codepoint (unicode character), fallback to '?' if not found
fun getGlyphInfo(font: Font, codepoint: Int) = Raylib.GetGlyphInfo(arena, font, codepoint)                                   // Get glyph font info data for a codepoint (unicode character), fallback to '?' if not found
fun getGlyphAtlasRec(font: Font, codepoint: Int) = Raylib.GetGlyphAtlasRec(arena, font, codepoint)                                // Get glyph rectangle in font atlas for a codepoint (unicode character), fallback to '?' if not found

// Text codepoints management functions (unicode characters)
char *loadUTF8(const int *codepoints, length: Int)                // Load UTF-8 text encoded from codepoints array
fun unloadUTF8(text: String) = Raylib.UnloadUTF8(text.allocate(arena))                                     // Unload UTF-8 text encoded from codepoints array
int *loadCodepoints(text: String, int *count)                // Load all codepoints from a UTF-8 text string, codepoints count returned by parameter
fun unloadCodepoints(int *codepoints): Unit                          // Unload codepoints data from memory
fun getCodepointCount(text: String) = Raylib.GetCodepointCount(text.allocate(arena))                        // Get total number of codepoints in a UTF-8 encoded string
fun getCodepoint(text: String, int *codepointSize): Int  {}          // Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
fun getCodepointNext(text: String, int *codepointSize): Int  {}      // Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
fun getCodepointPrevious(text: String, int *codepointSize): Int  {}  // Get previous codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
const char *codepointToUTF8(codepoint: Int, int *utf8Size)        // Encode one codepoint into UTF-8 byte array (array length returned as parameter)
