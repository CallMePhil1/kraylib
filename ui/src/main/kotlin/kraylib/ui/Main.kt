package kraylib.ui

import kraylib.core.*

class Main {

    private lateinit var bitmapFont: BitmapFont
    private lateinit var camera: OrthographicCamera
    private lateinit var content: Content
    private lateinit var freetypeGenerator: FreeTypeFontGenerator
    private lateinit var renderContext: RenderContext
    private lateinit var shapeRenderer: ShapeRenderer
    private lateinit var spriteBatch: SpriteBatch
    private lateinit var viewport: ScreenViewport
    private lateinit var texture: Texture

    private var renderBuffer: Int = 0

    override fun create() {
        camera = OrthographicCamera()
        viewport = ScreenViewport(camera)

        freetypeGenerator = FreeTypeFontGenerator(Gdx.files.internal("SourceCodePro-Regular.ttf"))
        val parameter = FreeTypeFontParameter()
        bitmapFont = freetypeGenerator.generateFont(parameter)

        shapeRenderer = ShapeRenderer()
        shapeRenderer.setAutoShapeType(true)
        spriteBatch = SpriteBatch()

        texture = Texture(Gdx.graphics.width, Gdx.graphics.height, Pixmap.Format.RGB888)
        renderBuffer = Gdx.gl.glGenRenderbuffer()

        renderContext = RenderContext(
            bitmapFont,
            camera,
            ScissorStack(),
            shapeRenderer,
            spriteBatch,
            StencilContext(Gdx.graphics.width / 2, Gdx.graphics.height / 2)
        )

        content = content {
            row {
                backgroundColor = Color.RED

                stencil { renderContext, constraint ->
                    renderContext.shapeRenderer.using(ShapeRenderer.ShapeType.Filled) {
                        rect(200f, 200f, 200f, 200f)
                    }
                }

                column {
                    button {
                        default {
                            backgroundColor = Color.BLUE
                        }

                        onHovered {
                            backgroundColor = Color.WHITE
                        }

                        text {
                            default {
                                borderThickness = 2f
                                borderColor = Color.BLACK

                                text = "Testing1"
                                padding = padding.copy(left = 50f)
                            }

                            onHovered {
                                borderColor = Color.RED
                            }
                        }
                    }

                    button {
                        backgroundColor = Color.RED
                        text {
                            text = "Testing2"
                        }
                    }
                }

                column {
                    button {
                        backgroundColor = Color.GREEN
                        text {
                            text = "Testing3"
                        }
                    }
                    button {
                        backgroundColor = Color.ORANGE
                        text {
                            text = "Testing4"
                        }
                    }
                }

                box {
                    circle {
                        radius = 80
//                        filled = false
                        backgroundColor = Color.RED
                    }
                }
            }
        }
    }

    override fun render() {
        Opengl.clearAll(Color.BLACK)

        Gdx.gl.glEnable(GL32.GL_STENCIL_TEST)
        Gdx.gl.glStencilMask(0xFF)

        renderContext.shapeRenderer.using(ShapeRenderer.ShapeType.Filled) {
            for (i in 1 .. 10) {
                color = Color((i * 16f) / 256, 0f, 0f, 1f)

                Gdx.gl.glStencilOp(GL32.GL_KEEP, GL32.GL_KEEP, GL32.GL_REPLACE)
                Gdx.gl.glStencilFunc(GL32.GL_LEQUAL, i, 0xFF)

                rect(30f * i, 30f * i, 100f, 100f)

//                Gdx.gl.glStencilOp(GL32.GL_KEEP, GL32.GL_KEEP, GL32.GL_REPLACE)
//                Gdx.gl.glStencilFunc(GL32.GL_EQUAL, i, 0xFF)
//
//                rect(30f * i, 30f * i, 100f, 100f)
            }
        }

        Gdx.gl.glDisable(GL32.GL_STENCIL_TEST)

//        val constraint = Constraint(
//            0, 0, Gdx.graphics.width, Gdx.graphics.height
//        )
//
//        content.draw(renderContext, constraint)
    }

    override fun resize(width: Int, height: Int) {
//        camera.viewportWidth = width.toFloat()
//        camera.viewportHeight = height.toFloat()
//        camera.position.x = width / 2f
//        camera.position.y = height / 2f
//        val vHeight = 4f
//        val vWidth = vHeight * width / height
//        camera.setToOrtho(false, vWidth, vHeight)
//        renderContext.projectionMatrix = camera.combined
//        renderContext.stencilContext.texture.texture.dispose()
//        renderContext.stencilContext.texture = TextureRegion(Texture(Gdx.graphics.width, Gdx.graphics.height, Pixmap.Format.RGB888))
//        renderContext.stencilContext.texture.flip(false, true)
//        camera.update()
    }
}
