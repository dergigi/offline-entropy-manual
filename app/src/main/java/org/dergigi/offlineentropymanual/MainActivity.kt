package org.dergigi.offlineentropymanual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.dergigi.offlineentropymanual.data.EntropyPaths
import org.dergigi.offlineentropymanual.data.ManualDocuments
import org.dergigi.offlineentropymanual.ui.AboutScreen
import org.dergigi.offlineentropymanual.ui.HomeScreen
import org.dergigi.offlineentropymanual.ui.PathScreen
import org.dergigi.offlineentropymanual.ui.PdfViewerScreen
import org.dergigi.offlineentropymanual.ui.theme.OfflineEntropyManualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OfflineEntropyManualTheme {
                OfflineEntropyManualApp()
            }
        }
    }
}

@Composable
fun OfflineEntropyManualApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onOpenPath = { path ->
                    navController.navigate("path/${path.id}")
                },
                onOpenAbout = { navController.navigate("about") },
            )
        }
        composable(
            route = "path/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) { entry ->
            val id = entry.arguments?.getString("id") ?: return@composable
            val path = EntropyPaths.byId(id)
            PathScreen(
                path = path,
                onOpenDocument = { document ->
                    navController.navigate("pdf/${document.id}")
                },
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "pdf/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) { entry ->
            val id = entry.arguments?.getString("id") ?: return@composable
            val document = ManualDocuments.byId(id)
            PdfViewerScreen(
                title = document.title,
                assetFileName = document.assetFileName,
                onBack = { navController.popBackStack() },
            )
        }
        composable("about") {
            AboutScreen(onBack = { navController.popBackStack() })
        }
    }
}
