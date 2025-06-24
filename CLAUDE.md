# CLAUDE.md

このファイルは、このリポジトリのコードを使用する際にClaude Code (claude.ai/code) にガイダンスを提供します。

## プロジェクト概要

**HoshiNote**は、Jetpack ComposeとMaterial 3で構築されたAndroidアプリケーションです。プロジェクトはKotlinを使用し、モダンなAndroid開発パターンに従っています。

- **パッケージ**: `com.example.hoshinote`
- **最小SDK**: 24 (Android 7.0)
- **ターゲットSDK**: 35 (Android 15)
- **UIフレームワーク**: Jetpack Compose with Material 3
- **アーキテクチャ**: Single Activity with Compose UI

## ビルドコマンド

```bash
# デバッグAPKをビルド
./gradlew assembleDebug

# 接続されたデバイスにビルドしてインストール
./gradlew installDebug

# クリーンビルド
./gradlew clean

# デバッグとリリース両方をビルド
./gradlew assemble

# 署名済みリリースAPKを生成
./gradlew assembleRelease
```

## テストコマンド

```bash
# 単体テストを実行
./gradlew test

# 計装テストを実行（デバイス/エミュレータが必要）
./gradlew connectedAndroidTest

# 全てのテストとチェックを実行
./gradlew check

# コードをLint
./gradlew lint
```

## プロジェクト構造

- **MainActivity.kt**: エッジトゥエッジサポート付きのComposeを使用するシングルアクティビティ
- **ui/theme/**: ダイナミックカラー対応のMaterial 3テーマ実装
- **標準的なAndroid構造**: src/main, src/test, src/androidTest

## 主要な依存関係

依存関係は `gradle/libs.versions.toml` で管理されています：
- Jetpack Compose BOM (2025.06.00)
- AndroidX Core KTX (1.16.0)
- Activity Compose (1.10.1)
- Material 3
- Lifecycle Runtime KTX (2.9.1)

## 開発メモ

- コンパイルにはJava 11+が必要
- ComposeにはKotlin Compiler Extension Version 1.5.14を使用
- Android 12+のダイナミックテーマに対応
- リリースビルド用のProGuard設定が利用可能