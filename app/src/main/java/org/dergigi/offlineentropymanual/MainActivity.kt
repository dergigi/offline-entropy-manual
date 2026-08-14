package org.dergigi.offlineentropymanual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.dergigi.offlineentropymanual.data.AppSettings
import org.dergigi.offlineentropymanual.data.EntropyPaths
import org.dergigi.offlineentropymanual.data.ManualDocuments
import org.dergigi.offlineentropymanual.data.TextSizePreference
import org.dergigi.offlineentropymanual.data.ThemePreference
import org.dergigi.offlineentropymanual.ui.AboutScreen
import org.dergigi.offlineentropymanual.ui.AirgappedBip39ToolScreen
import org.dergigi.offlineentropymanual.ui.Backup321Screen
import org.dergigi.offlineentropymanual.ui.HomeScreen
import org.dergigi.offlineentropymanual.ui.PathScreen
import org.dergigi.offlineentropymanual.ui.PdfViewerScreen
import org.dergigi.offlineentropymanual.ui.SettingsScreen
import org.dergigi.offlineentropymanual.ui.SplashScreen
import org.dergigi.offlineentropymanual.ui.WhatIsEntropyScreen
import org.dergigi.offlineentropymanual.ui.theme.OfflineEntropyManualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            var themePreference by remember { mutableStateOf(AppSettings.theme(context)) }
            var textSizePreference by remember { mutableStateOf(AppSettings.textSize(context)) }

            OfflineEntropyManualTheme(
                themePreference = themePreference,
                textSizePreference = textSizePreference,
            ) {
                OfflineEntropyManualApp(
                    themePreference = themePreference,
                    textSizePreference = textSizePreference,
                    onThemeChange = { value ->
                        AppSettings.setTheme(context, value)
                        themePreference = value
                    },
                    onTextSizeChange = { value ->
                        AppSettings.setTextSize(context, value)
                        textSizePreference = value
                    },
                )
            }
        }
    }
}

@Composable
fun OfflineEntropyManualApp(
    themePreference: ThemePreference,
    textSizePreference: TextSizePreference,
    onThemeChange: (ThemePreference) -> Unit,
    onTextSizeChange: (TextSizePreference) -> Unit,
) {
    val navController = rememberNavController()
    val openAirgapped = {
        navController.navigate("airgapped") {
            launchSingleTop = true
        }
    }
    val openBackup321 = {
        navController.navigate("backup-321") {
            launchSingleTop = true
        }
    }
    val openWhatIsEntropy = {
        navController.navigate("what-is-entropy") {
            launchSingleTop = true
        }
    }
    val openSettings = {
        navController.navigate("settings") {
            launchSingleTop = true
        }
    }

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(
                onFinished = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
            )
        }
        composable("home") {
            HomeScreen(
                onOpenPath = { path ->
                    navController.navigate("path/${path.id}")
                },
                onOpenAbout = { navController.navigate("about") },
                onOpenSettings = openSettings,
                onOpenWhatIsEntropy = openWhatIsEntropy,
                onOpenAirgappedDevice = openAirgapped,
                onOpenBackup321 = openBackup321,
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
                onOpenAirgappedDevice = openAirgapped,
                onOpenBackup321 = openBackup321,
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
                attribution = document.attribution,
                assetFileName = document.assetFileName,
                textSizeScale = textSizePreference.scale,
                onOpenAirgappedDevice = openAirgapped,
                onOpenBackup321 = openBackup321,
                onOpenSettings = openSettings,
                onBack = { navController.popBackStack() },
            )
        }
        composable("about") {
            AboutScreen(
                onBack = { navController.popBackStack() },
                onOpenAirgappedDevice = openAirgapped,
                onOpenBackup321 = openBackup321,
            )
        }
        composable("settings") {
            SettingsScreen(
                themePreference = themePreference,
                textSizePreference = textSizePreference,
                onThemeChange = onThemeChange,
                onTextSizeChange = onTextSizeChange,
                onBack = { navController.popBackStack() },
            )
        }
        composable("airgapped") {
            AirgappedBip39ToolScreen(
                onBack = { navController.popBackStack() },
                onOpenAirgappedDevice = openAirgapped,
                onOpenBackup321 = openBackup321,
            )
        }
        composable("backup-321") {
            Backup321Screen(
                onBack = { navController.popBackStack() },
            )
        }
        composable("what-is-entropy") {
            WhatIsEntropyScreen(
                onOpenBip39 = {
                    navController.navigate("pdf/bip39") {
                        launchSingleTop = true
                    }
                },
                onOpenAirgappedDevice = openAirgapped,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
